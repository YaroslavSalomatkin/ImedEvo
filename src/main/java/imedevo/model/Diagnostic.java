package imedevo.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "diagnostics")
public class Diagnostic {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "name")
  private String name;
  @Column(name = "medical_license")
  private String medicalLicense;
  @Column(name = "address")
  private String address;
  @Column(name = "phone")
  private String phone;
  @Column(name = "email")
  private String email;
  @Column(name = "days_of_admission")
  private String daysOfAdmission;
  @Column(name = "working_hours")
  private String workingHours;
  @Column(name = "description")
  private String description;
  @Column(name = "services")
  private String services;
  @Column(name = "rating")
  private Double rating;
  @Column(name = "latitude")
  private Double latitude;
  @Column(name = "longitude")
  private Double longitude;
  @Column(name = "logo")
  private String logo;
  @Column(name = "registration_date")
  private String registrationDate;

  public Diagnostic() {

  }

  public Diagnostic(String name, String medicalLicense, String address, String phone, String email,
      String daysOfAdmission, String workingHours, String description, String services,
      Double latitude, Double longitude, String logo) {
    this.name = name;
    this.medicalLicense = medicalLicense;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.daysOfAdmission = daysOfAdmission;
    this.workingHours = workingHours;
    this.description = description;
    this.services = services;
    this.latitude = latitude;
    this.longitude = longitude;
    this.logo = logo;
  }

  @Override
  public String toString() {
    return "Diagnostic{" +
        "id=" + id +
        ", rating=" + rating +
        ", name='" + name + '\'' +
        ", medicalLicense='" + medicalLicense + '\'' +
        ", address='" + address + '\'' +
        ", phone='" + phone + '\'' +
        ", email='" + email + '\'' +
        ", daysOfAdmission='" + daysOfAdmission + '\'' +
        ", workingHours='" + workingHours + '\'' +
        ", description='" + description + '\'' +
        ", services='" + services + '\'' +
        ", latitude=" + latitude +
        ", longitude=" + longitude +
        ", logo='" + logo + '\'' +
        ", registrationDate=" + registrationDate +
        '}';
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Double getRating() {
    return rating;
  }

  public void setRating(Double rating) {
    this.rating = rating;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMedicalLicense() {
    return medicalLicense;
  }

  public void setMedicalLicense(String medicalLicense) {
    this.medicalLicense = medicalLicense;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDaysOfAdmission() {
    return daysOfAdmission;
  }

  public void setDaysOfAdmission(String daysOfAdmission) {
    this.daysOfAdmission = daysOfAdmission;
  }

  public String getWorkingHours() {
    return workingHours;
  }

  public void setWorkingHours(String workingHours) {
    this.workingHours = workingHours;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getServices() {
    return services;
  }

  public void setServices(String services) {
    this.services = services;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

  public String getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(String registrationDate) {
    this.registrationDate = registrationDate;
  }
}
