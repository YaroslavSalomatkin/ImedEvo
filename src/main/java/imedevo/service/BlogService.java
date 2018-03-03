package imedevo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import imedevo.httpStatuses.BlogNotFoundException;
import imedevo.httpStatuses.BlogStatus;
import imedevo.model.Blog;
import imedevo.repository.BlogRepository;
import imedevo.repository.UserRepository;
import imedevo.repository.UserRoleRepository;
import javax.transaction.Transactional;

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

    map.put("status", BlogStatus.ADD_OK);
    map.put("blog", blogRepository.save(blog));
    return map;
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
      map.put("status", BlogStatus.EDIT_BLOG_SUCCESS);
      map.put("blog", blogRepository.save(blogFromDb));
      return map;
    }
    return map;
  }

  @Transactional
  public Map<String, Object> deleteBlog(long id)
      throws BlogNotFoundException {
    Map<String, Object> map = new HashMap<>();

    if (blogRepository.exists(id)) {
      blogRepository.delete(id);
      map.put("status", BlogStatus.DELETE_SUCCESS);
    } else {
      throw new BlogNotFoundException();
    }
    return map;
  }
}



