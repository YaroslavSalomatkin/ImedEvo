package imedevo.repository;

import org.springframework.data.repository.CrudRepository;

import imedevo.model.Discount;

public interface DiscountRepository extends CrudRepository<Discount, Long>{

  Discount findByName(String name);

}
