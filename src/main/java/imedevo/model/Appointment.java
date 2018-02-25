package imedevo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "appointments")
public class Appointment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "doctor_id")
  private Long doctorId;

  @Column(name = "time")
  private String time;

  @Column(name = "date")
  private String date;

  @Column(name = "is_approved")
  private boolean isApproved;

  @Column(name = "status")
  private String status;

  @Column(name = "doctor_name")
  private String doctorName;

  public Appointment(Long id, Long userId, Long doctorId, String time, String date,
      boolean isAproved, String status, String doctorName) {
    this.id = id;
    this.userId = userId;
    this.doctorId = doctorId;
    this.time = time;
    this.date = date;
    this.isApproved = isAproved;
    this.status = status;
    this.doctorName = doctorName;
  }

  public Appointment() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getUserId() {
    return userId;
  }

  public Long getDoctorId() {
    return doctorId;
  }

  public void setDoctorId(Long doctorId) {
    this.doctorId = doctorId;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public boolean isApproved() {
    return isApproved;
  }

  public void setApproved(boolean approved) {
    isApproved = approved;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getDoctorName() {
    return doctorName;
  }

  public void setDoctorName(String doctorName) {
    this.doctorName = doctorName;
  }

  @Override
  public String toString() {
    return "Appointment{" +
        "id=" + id +
        ", userId=" + userId +
        ", doctorId=" + doctorId +
        ", time='" + time + '\'' +
        ", date='" + date + '\'' +
        ", isApproved=" + isApproved +
        ", status='" + status + '\'' +
        ", doctorName='" + doctorName + '\'' +
        '}';
  }
}
