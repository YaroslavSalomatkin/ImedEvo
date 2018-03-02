package imedevo.repository;

import imedevo.model.Doctor;
import imedevo.model.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Long> {

  Image findById(long id);

  Image findByUserId(long userId);
}
