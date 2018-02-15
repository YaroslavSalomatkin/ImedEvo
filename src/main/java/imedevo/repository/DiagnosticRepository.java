package imedevo.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

import imedevo.model.Diagnostic;

public interface DiagnosticRepository extends CrudRepository<Diagnostic, Long> {

  Optional<Diagnostic> findById(Long id);

  Diagnostic findByEmail(String email);

  List<Diagnostic> findByName(String name);
}