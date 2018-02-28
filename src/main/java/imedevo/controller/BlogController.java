package imedevo.controller;

import imedevo.httpStatuses.BlogNotFoundException;
import imedevo.model.Blog;
import imedevo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/getall")
    public List<Blog> getAllBlogs() {
        return blogService.getAll();

    }
    @GetMapping("/{id}")
    public Blog getClinicById(@PathVariable Long id) throws BlogNotFoundException {
        return blogService.getById(id);
    }

    @PostMapping("/admin/createblog")
    @PreAuthorize("hasAnyRole('BLOGGER', 'SUPER_ADMIN')")
    public Map<String, Object> createBlog(@RequestBody Blog blog) {
        return blogService.save(blog);
    }


    @PutMapping("/admin/updateblog")
    @PreAuthorize("hasAnyRole('BLOGGER', 'SUPER_ADMIN')")
    public Map<String, Object> updateBlog(@RequestBody Blog blog) { return blogService.updateBlog(blog);
    }


    @DeleteMapping("/admin/deleteblog/{id}")
    @PreAuthorize("hasAnyRole('BLOGGER', 'SUPER_ADMIN')")
    public void deleteClinic(@PathVariable Long id) throws BlogNotFoundException {
        blogService.deleteBlog(id);
    }

}
