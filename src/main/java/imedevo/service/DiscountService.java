package imedevo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import imedevo.model.Discount;
import imedevo.repository.DiscountRepository;

@Service
public class DiscountService {

  @Autowired
  private DiscountRepository discountRepository;

  public List<Discount> getAllDiscounts() {
    return (List<Discount>) discountRepository.findAll();
  }

  public Discount getById(Long id) {
    return discountRepository.findOne(id);
  }

  public Map<String, Object> addDiscount(Discount discount) {
    Map<String, Object> map = new HashMap<>();
    discount.setDateOfEntry(Date.valueOf(LocalDate.now()));
    map.put("status", "Discount add successful");
    map.put("discount", discountRepository.save(discount));
    return map;
  }

  public Map<String, Object> updateDiscount(Discount updatedDiscount) {
    Map<String, Object> map = new HashMap<>();
    Discount discountFromDb = discountRepository.findOne(updatedDiscount.getId());
    Field[] fields = updatedDiscount.getClass().getDeclaredFields();
    AccessibleObject.setAccessible(fields, true);
    for (Field field : fields) {
      Object discountFromDbValue = ReflectionUtils.getField(field, updatedDiscount);
      if (discountFromDbValue != null) {
        ReflectionUtils.setField(field, discountFromDb, discountFromDbValue);
      }
    }
    map.put("status", "Edit OK");
    map.put("discount", discountRepository.save(discountFromDb));
    return map;
  }

  public void deleteDiscount(Long id) {
    discountRepository.delete(id);
  }
}
