package imedevo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import imedevo.httpStatuses.DiscountNotFoundException;
import imedevo.model.Discount;
import imedevo.service.DiscountService;

@RestController
@RequestMapping("/discount")
public class DiscountController {

  @Autowired
  private DiscountService discountService;

  @GetMapping("/getall")
  public List<Discount> getAll() {
    return discountService.getAllDiscounts();
  }

  @GetMapping("/{id}")
  public Discount getById(@PathVariable Long id) throws DiscountNotFoundException {
    return discountService.getById(id);
  }

  @GetMapping("/byclinics")
  public List<Discount> getByClinics(@RequestParam ("clinicid") Long clinicId) {
    return discountService.getByClinics(clinicId);
  }

  @GetMapping("/bydoctors")
  public List<Discount> getByDoctors(@RequestParam ("doctorid") Long doctorId) {
    return discountService.getByDoctors(doctorId);
  }

  @PostMapping("/admin/creatediscount")
  @PreAuthorize("hasAnyRole('CLINIC_ADMIN', 'SUPER_ADMIN')")
  public Map<String, Object> addDiscount(@RequestBody Discount discount) {
    return discountService.addDiscount(discount);
  }

  @PutMapping("/admin/updatediscount")
  @PreAuthorize("hasAnyRole('CLINIC_ADMIN', 'SUPER_ADMIN')")
  public Map<String, Object> updateDiscount(@RequestBody Discount discount)
      throws DiscountNotFoundException {
    return discountService.updateDiscount(discount);
  }

  @DeleteMapping("/admin/deletediscount")
  @PreAuthorize("hasAnyRole('CLINIC_ADMIN', 'SUPER_ADMIN')")
  public void deleteDiscount(@RequestParam("id") Long id) throws DiscountNotFoundException {
    discountService.deleteDiscount(id);
  }
}
