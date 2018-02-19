package imedevo.repository;

import imedevo.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BlogRepository  extends JpaRepository <Blog, Long> {

}
