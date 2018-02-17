package imedevo.model;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "discount")
public class Discount {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "name")
  private String name;
  @Column(name = "description")
  private String description;
  @Column(name = "clinic_id")
  private Long clinicId;
  @Column(name = "doctor_id")
  private Long doctorId;
  @Column(name = "end_date")
  private Date endDate;
  @Column(name = "price")
  private BigDecimal price;
  @Column(name = "discount")
  private String discount;
  @Column(name = "date_of_entry")
  private String dateOfEntry;

  public Discount() {
  }

  public Discount(Long id, String name, String description, Long clinicId, Long doctorId,
      Date endDate, BigDecimal price, String discount, String dateOfEntry) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.clinicId = clinicId;
    this.doctorId = doctorId;
    this.endDate = endDate;
    this.price = price;
    this.discount = discount;
    this.dateOfEntry = dateOfEntry;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Long getClinicId() {
    return clinicId;
  }

  public void setClinicId(Long clinicId) {
    this.clinicId = clinicId;
  }

  public Long getDoctorId() {
    return doctorId;
  }

  public void setDoctorId(Long doctorId) {
    this.doctorId = doctorId;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getDiscount() {
    return discount;
  }

  public void setDiscount(String discount) {
    this.discount = discount;
  }

  public String getDateOfEntry() {
    return dateOfEntry;
  }

  public void setDateOfEntry(String dateOfEntry) {
    this.dateOfEntry = dateOfEntry;
  }
}
