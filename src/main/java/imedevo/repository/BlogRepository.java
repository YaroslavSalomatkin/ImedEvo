package imedevo.repository;

import imedevo.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogRepository  extends JpaRepository <Blog, Long> {
    Optional<Blog> findById(Long id);


}
