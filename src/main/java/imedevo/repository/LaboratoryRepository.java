package imedevo.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

import imedevo.model.Laboratory;

public interface LaboratoryRepository extends CrudRepository<Laboratory, Long> {

  Optional<Laboratory> findById(Long id);

  Laboratory findByEmail(String email);

  List<Laboratory> findByName(String name);
}