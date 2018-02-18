package imedevo.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

import imedevo.model.Discount;

public interface DiscountRepository extends CrudRepository<Discount, Long> {

  Discount findByName(String name);

  List<Discount> findByClinicId(Long clinicId);

  List<Discount> findByDoctorId(Long doctorId);

}
