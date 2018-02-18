package imedevo.service;

import imedevo.httpStatuses.BlogNotFoundException;
import imedevo.httpStatuses.BlogStatus;
import imedevo.model.Blog;
import imedevo.model.Role;

import imedevo.model.UserRole;
import imedevo.repository.BlogRepository;
import imedevo.repository.UserRepository;
import imedevo.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;


import javax.transaction.Transactional;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private RolesService rolesService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;


    public List<Blog> getAll() {
        return blogRepository.findAll();
    }

    public Blog getById(long id) throws BlogNotFoundException {
        Blog blog = blogRepository.findOne(id);
        if (blog == null) {
            throw new BlogNotFoundException();
        }

        return blog;
    }

    @Transactional
    public Map<String, Object> save(Blog blog) {
        Map<String, Object> map = new HashMap<>();

        if (blog.getPicture() == null) {
            map.put("status", BlogStatus.REGISTRATION_ERROR_EMPTY_PICTURE);
            return map;
        }
        if (blog.getFirstName() == null) {
            map.put("status", BlogStatus.REGISTRATION_ERROR_EMPTY_FIRSTNAME);

            return map;
        }
        if (blog.getLastName() == null) {
            map.put("status", BlogStatus.REGISTRATION_ERROR_EMPTY_LASTNAME);

            return map;
        }
        if (blog.getArticleName() == null) {
            map.put("status", BlogStatus.REGISTRATION_ERROR_EMPTY_ARTICLENAME);

            return map;
        }

        List<UserRole> userRoles = rolesService.getUserRoles(userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .getId());
        boolean success = false;
        for (UserRole userRole : userRoles) {
            if (userRole.getRole().equals(Role.BLOGGER)) {
                success = true;
            }
        }
        if (success) {
            map.put("status", "Success");
            map.put("blog", blogRepository.save(blog));
            return map;
        } else {
            map.put("status", "Create blog error");
            return map;
        }

    }

    @Transactional
    public Map<String, Object> updateBlog(Blog updateBlog) {
        Map<String, Object> map = new HashMap<>();

        Blog blogFromDb = blogRepository.findOne(updateBlog.getId());
        if (blogFromDb == null) {
            map.put("status", BlogStatus.NOT_FOUND);
        } else {
            Field[] fields = updateBlog.getClass().getDeclaredFields();
            AccessibleObject.setAccessible(fields, true);
            for (Field field : fields) {
                Object blogFromDbValue = ReflectionUtils.getField(field, updateBlog);
                if (blogFromDbValue != null) {
                    ReflectionUtils.setField(field, blogFromDb, blogFromDbValue);
                }
            }

            List<UserRole> userRoles = rolesService.getUserRoles(userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                    .getId());
            boolean success = false;
            for (UserRole userRole : userRoles) {
                if (userRole.getRole().equals(Role.BLOGGER) || (userRole.getRole().equals(Role.SUPER_ADMIN))) {
                    success = true;
                }
            }
            if (success) {
                map.put("status", BlogStatus.EDIT_BLOG_SUCCESS);
                map.put("blog", blogRepository.save(blogFromDb));
                return map;
            } else {
                map.put("status", BlogStatus.EDIT_BLOG_ERROR);
                return map;
            }

        }
        return map;
    }

    @Transactional
    public Map<String, Object> deleteBlog(long id)
            throws BlogNotFoundException {
        Map<String, Object> map = new HashMap<>();

        List<UserRole> userRoles = rolesService.getUserRoles(userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .getId());
        boolean success = false;
        if (blogRepository.findOne(id) != null) {
            for (UserRole userRole : userRoles) {
                if (userRole.getRole().equals(Role.BLOGGER) || (userRole.getRole().equals(Role.SUPER_ADMIN))) {
                    success = true;
                }
            }
            if (success) {
                blogRepository.delete(id);
            }
        } else {
            throw new BlogNotFoundException();
        }
     return map;
    }
}



