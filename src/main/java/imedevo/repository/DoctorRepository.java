package imedevo.repository;


import imedevo.model.Doctor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

  Doctor findById(long id);

  Doctor findByUserId(long userId);

  List<Doctor> findAll();

  @Query(value = "SELECT * "
      + "FROM doctors d "
      + "JOIN users u ON d.user_id = u.id "
      + "WHERE (u.last_name) LIKE (?1)",
      nativeQuery = true)
  List<Doctor> findByLastname(String doctorParams);

  @Query(value = "SELECT * "
      + "FROM doctors d "
      + "JOIN users u ON d.user_id = u.id "
      + "WHERE (u.first_name) LIKE (?1)",
      nativeQuery = true)
  List<Doctor> findByFirstname(String doctorParams);
}
