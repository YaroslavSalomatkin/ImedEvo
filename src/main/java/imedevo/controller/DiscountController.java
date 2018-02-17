package imedevo.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
  public Discount getById(@PathVariable Long id) {
    return discountService.getById(id);
  }

  @PostMapping("/creatediscount")
  public Map<String, Object> addDiscount(@RequestBody Discount discount) {
    return discountService.addDiscount(discount);
  }

  @PutMapping("/updatediscount")
  public Map<String, Object> updateDiscount(@RequestBody Discount discount) {
    return discountService.updateDiscount(discount);
  }

  @DeleteMapping("/deletediscount")
  public void deleteDiscount(@RequestParam("id") Long id) {
    discountService.deleteDiscount(id);
  }
}
