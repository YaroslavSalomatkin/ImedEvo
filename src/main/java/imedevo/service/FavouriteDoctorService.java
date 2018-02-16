package imedevo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import imedevo.httpStatuses.UserNotFoundException;
import imedevo.httpStatuses.UserStatus;
import imedevo.model.Doctor;
import imedevo.model.FavouriteDoctor;
import imedevo.repository.DoctorRepository;
import imedevo.repository.FavouriteDoctorsRepository;
import imedevo.repository.UserRepository;
import javax.transaction.Transactional;

@Service
public class FavouriteDoctorService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private DoctorRepository doctorRepository;

  @Autowired
  private RolesService rolesService;

  @Autowired
  private FavouriteDoctorsRepository favouriteDoctorsRepository;

  public List<Doctor> getById(long id) throws UserNotFoundException {
    List<Doctor> doctors = new ArrayList<>();
    List<FavouriteDoctor> favDoctors = favouriteDoctorsRepository.findByUserId(id);
    if (!favDoctors.isEmpty()) {
      for (FavouriteDoctor favouriteDoctor : favDoctors) {
        Doctor doctor = doctorRepository.findByUserId(favouriteDoctor.getDoctorId());
        doctor.getUser().setUserRoles(rolesService.getUserRoles(doctor.getUserId()));
        doctors.add(doctor);
      }
    }
    return doctors;
  }

  @Transactional
  public Map<String, Object> addFavouriteDoctor(FavouriteDoctor favouriteDoctor) {
    Map<String, Object> map = new HashMap<>();
    if (favouriteDoctor != null) {
      favouriteDoctor.setDateOfEntry(Date.valueOf(LocalDate.now()));
      map.put("status", UserStatus.FAVOURITE_DOCTOR_ADD_OK);
      map.put("favourite-doctor", favouriteDoctorsRepository.save(favouriteDoctor));
    } else {
      map.put("status", UserStatus.FAVOURITE_DOCTOR_ADD_ERROR);
    }
    return map;
  }

  @Transactional
  public Map<String, Object> deleteFavouriteDoctor(long id) {
    Map<String, Object> map = new HashMap<>();
    if (favouriteDoctorsRepository.findOne(id) != null) {
      favouriteDoctorsRepository.delete(id);
      map.put("status", UserStatus.FAVOURITE_DOCTOR_DELETE_OK);
    } else {
      map.put("status", UserStatus.FAVOURITE_DOCTOR_NOT_FOUND);
    }
    return map;
  }
}
