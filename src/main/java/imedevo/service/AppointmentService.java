package imedevo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import imedevo.httpStatuses.AccessDeniedException;
import imedevo.httpStatuses.UserStatus;
import imedevo.model.Appointment;
import imedevo.repository.AppointmentRepository;
import imedevo.repository.UserRepository;

@Service
public class AppointmentService {

  @Autowired
  private AppointmentRepository appointmentRepository;

  @Autowired
  private UserRepository userRepository;

  Map<String, Object> map = new HashMap<>();

  public List<Appointment> getByUserId(long id) {
    return appointmentRepository.findByUserId(id);
  }

  public List<Appointment> getByDoctorId(long id) {
    return appointmentRepository.findByDoctorId(id);
  }

  public Map<String, Object> addAppointment(Appointment appointment) {

    if (appointment == null) {
      map.put("status", UserStatus.APPOINTMENT_INVALID);
    } else if (appointment.getUserId() == null || appointment.getDoctorId() == null
        || appointment.getDate() == null
        || appointment.getTime() == null) {
      map.put("status", UserStatus.APPOINTMENT_INVALID);
    } else {
      appointment.setStatus("New appointment");
      appointment
          .setDoctorName(userRepository.findOne(appointment.getDoctorId()).getFirstName() + " "
              + userRepository.findOne(appointment.getDoctorId()).getLastName());
      map.put("status", UserStatus.ADD_NEW_APPOINTMENT_OK);
      map.put("appointment", appointmentRepository.save(appointment));
    }
    return map;
  }

  public Map<String, Object> updateAppointment(Appointment updatedAppointment)
      throws AccessDeniedException {
    /** this is security checking */
//    if (userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
//        .getId() != updatedAppointment.getUserId()
//        || userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
//        .getId() != updatedAppointment.getDoctorId()) {
//      throw new AccessDeniedException();
//    }
    Map<String, Object> map = new HashMap<>();

    if (updatedAppointment.getId() == null) {
      map.put("status", UserStatus.APPOINTMENT_EDITORING_ERROR);
      return map;
    }

    if (updatedAppointment.getDoctorId() != null) {
      updatedAppointment.setDoctorName(
          userRepository.findOne(updatedAppointment.getDoctorId()).getFirstName() + " "
              + userRepository.findOne(updatedAppointment.getDoctorId()).getLastName());
    }

    Appointment appointmentFromDb = appointmentRepository.findOne(updatedAppointment.getId());
    if (appointmentFromDb == null) {
      map.put("status", UserStatus.APPOINTMENT_NOT_FOUND);
    } else {
      Field[] fields = updatedAppointment.getClass().getDeclaredFields();
      AccessibleObject.setAccessible(fields, true);
      for (Field field : fields) {
        if (field.getName().equals("id") || field.getName().equals("userId")) {
          continue;
        }
        Object appointmentFromDbValue = ReflectionUtils.getField(field, updatedAppointment);
        if (appointmentFromDbValue != null) {
          ReflectionUtils.setField(field, appointmentFromDb, appointmentFromDbValue);
        }
      }

      if (updatedAppointment.isApproved() && !appointmentFromDb.isApproved()) {
        appointmentFromDb.setStatus("Appointment approved");
      }
      map.put("status", UserStatus.APPOINTMENT_EDITED_OK);
      map.put("appointment", appointmentRepository.save(appointmentFromDb));
    }
    return map;
  }

  public Map<String, Object> deleteAppointment(long id) throws AccessDeniedException {
    /** this is security checking */
//    if (userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
//        .getId() != appointmentRepository.findOne(id).getUserId()
//        || userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
//        .getId() != appointmentRepository.findOne(id).getDoctorId()) {
//      throw new AccessDeniedException();
//    }

    Map<String, Object> map = new HashMap<>();
    if (appointmentRepository.findOne(id) != null) {
      appointmentRepository.delete(id);
      map.put("status", UserStatus.APPOINTMENT_DELETE);
    } else {
      map.put("status", UserStatus.APPOINTMENT_NOT_FOUND);
    }
    return map;
  }
}
