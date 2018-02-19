package imedevo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import imedevo.httpStatuses.DiscountNotFoundException;
import imedevo.httpStatuses.DiscountStatus;
import imedevo.model.Discount;
import imedevo.repository.DiscountRepository;

@Service
public class DiscountService {

  @Autowired
  private DiscountRepository discountRepository;

  public List<Discount> getAllDiscounts() {
    return (List<Discount>) discountRepository.findAll();
  }

  public Discount getById(Long id) throws DiscountNotFoundException {
    if (discountRepository.findOne(id) != null) {
      return discountRepository.findOne(id);
    }
    throw new DiscountNotFoundException();
  }

  public List<Discount> getByClinics(Long clinicId) {
    return discountRepository.findByClinicId(clinicId);
  }

  public List<Discount> getByDoctors(Long doctorId) {
    return discountRepository.findByDoctorId(doctorId);
  }

  public Map<String, Object> addDiscount(Discount discount) {
    Map<String, Object> map = new HashMap<>();

    if (discount.getName() == null || discount.getName().length() < 5) {
      map.put("status", DiscountStatus.ERROR_EMPTY_NAME);
      return map;
    }

    if (discount.getDescription() == null || discount.getDescription().length() < 5) {
      map.put("status", DiscountStatus.ERROR_EMPTY_DESCRIPTION);
      return map;
    }

    discount.setDateOfEntry(LocalDate.now().toString());
    map.put("status", DiscountStatus.ADD_DISCOUNT_OK);
    map.put("discount", discountRepository.save(discount));
    return map;
  }

  public Map<String, Object> updateDiscount(Discount updatedDiscount)
      throws DiscountNotFoundException {

    Map<String, Object> map = new HashMap<>();

    if (updatedDiscount.getId() == null) {
      map.put("status", DiscountStatus.EDIT_DISCOUNT_ERROR);
      return map;
    }
    if (discountRepository.findOne(updatedDiscount.getId()) == null) {
      throw new DiscountNotFoundException();
    }

    Discount discountFromDb = discountRepository.findOne(updatedDiscount.getId());
    Field[] fields = updatedDiscount.getClass().getDeclaredFields();
    AccessibleObject.setAccessible(fields, true);
    for (Field field : fields) {
      Object discountFromDbValue = ReflectionUtils.getField(field, updatedDiscount);
      if (discountFromDbValue != null) {
        ReflectionUtils.setField(field, discountFromDb, discountFromDbValue);
      }
    }
    map.put("status", DiscountStatus.EDIT_DISCOUNT_SUCCESS);
    map.put("discount", discountRepository.save(discountFromDb));
    return map;
  }

  public void deleteDiscount(Long id) throws DiscountNotFoundException {
    if (discountRepository.findOne(id) == null) {
      throw new DiscountNotFoundException();
    } else {
      discountRepository.delete(id);
    }
  }
}
