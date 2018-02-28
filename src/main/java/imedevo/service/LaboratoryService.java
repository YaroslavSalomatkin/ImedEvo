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
import java.util.Optional;

import imedevo.httpStatuses.HospitalStatus;
import imedevo.httpStatuses.NoSuchClinicException;
import imedevo.model.Geoposition;
import imedevo.model.Laboratory;
import imedevo.repository.LaboratoryRepository;
import javax.transaction.Transactional;

@Service
public class LaboratoryService {

  @Autowired
  private LaboratoryRepository laboratoryRepository;

  @Autowired
  private GeocodingService geocoding;

  public List<Laboratory> getAll() {
    return (List<Laboratory>) laboratoryRepository.findAll();
  }

  public Laboratory getById(long id) throws NoSuchClinicException {
    Laboratory laboratory = laboratoryRepository.findOne(id);
    if (laboratory == null) {
      throw new NoSuchClinicException();
    }
    return laboratory;
  }

  @Transactional
  public Map<String, Object> saveLaboratory(Laboratory laboratory) {
    Map<String, Object> map = new HashMap<>();

    if (laboratory.getLogo() == null) {
      map.put("status", HospitalStatus.REGISTRATION_ERROR_EMPTY_LOGO);
      return map;
    }

    if (laboratory.getName() == null || laboratory.getName().trim().length() < 3) {
      map.put("status", HospitalStatus.REGISTRATION_ERROR_EMPTY_NAME);
      return map;
    }

    if (laboratory.getMedicalLicense() == null) {
      map.put("status", HospitalStatus.REGISTRATION_ERROR_EMPTY_MEDICAL_LECENSE);
      return map;
    }

    if (laboratory.getAddress() == null || laboratory.getAddress().trim().length() < 8) {
      map.put("status", HospitalStatus.REGISTRATION_ERROR_EMPTY_ADDRESS);
      return map;
    }

    if (laboratory.getDescription() == null || laboratory.getDescription().trim().length() < 5) {
      map.put("status", HospitalStatus.REGISTRATION_ERROR_EMPTY_DESCRIPTION);
      return map;
    }

    Geoposition geoposition = geocoding.getGeopositionByAddress(laboratory.getAddress());
    laboratory.setAddress(geoposition.getAddress());
    laboratory.setLatitude(geoposition.getLat());
    laboratory.setLongitude(geoposition.getLng());
    laboratory.setRegistrationDate(LocalDate.now().toString());
    map.put("status", HospitalStatus.REGISTRATION_OK);
    map.put("laboratory", laboratoryRepository.save(laboratory));
    return map;
  }

  @Transactional
  public Map<String, Object> updateLaboratory(Laboratory updatedLaboratory) {
    Map<String, Object> map = new HashMap<>();

    if (updatedLaboratory.getId() == null) {
      map.put("status", HospitalStatus.EDIT_PROFILE_ERROR);
      return map;
    }

    if (updatedLaboratory.getAddress() != null) {
      Geoposition geoposition = geocoding.getGeopositionByAddress(updatedLaboratory.getAddress());
      updatedLaboratory.setAddress(geoposition.getAddress());
      updatedLaboratory.setLatitude(geoposition.getLat());
      updatedLaboratory.setLongitude(geoposition.getLng());
    }

    Laboratory laboratoryFromDb = laboratoryRepository.findOne(updatedLaboratory.getId());
    if (laboratoryFromDb == null) {
      map.put("status", HospitalStatus.NOT_FOUND);
    } else {
      Field[] fields = updatedLaboratory.getClass().getDeclaredFields();
      AccessibleObject.setAccessible(fields, true);
      for (Field field : fields) {
        if (field.getName().equals("id") || field.getName().equals("rating") ||
            field.getName().equals("registrationDate") || field.getName().equals("latitude") ||
            field.getName().equals("longitude")) {
          continue;
        }

        if (field.getName().equals("name")) {
          if (ReflectionUtils.getField(field, updatedLaboratory) != null &&
              ReflectionUtils.getField(field, updatedLaboratory).toString().trim().length() < 3) {
            map.put("status", HospitalStatus.REGISTRATION_ERROR_EMPTY_NAME);
            return map;
          }
        }

        if (field.getName().equals("medicalLicense")) {
          if (ReflectionUtils.getField(field, updatedLaboratory) != null &&
              ReflectionUtils.getField(field, updatedLaboratory).toString().trim().length() < 5) {
            map.put("status", HospitalStatus.REGISTRATION_ERROR_EMPTY_MEDICAL_LECENSE);
            return map;
          }
        }

        if (field.getName().equals("address")) {
          if (ReflectionUtils.getField(field, updatedLaboratory) != null &&
              ReflectionUtils.getField(field, updatedLaboratory).toString().trim().length() < 5) {
            map.put("status", HospitalStatus.REGISTRATION_ERROR_EMPTY_ADDRESS);
            return map;
          }
        }

        if (field.getName().equals("description")) {
          if (ReflectionUtils.getField(field, updatedLaboratory) != null &&
              ReflectionUtils.getField(field, updatedLaboratory).toString().trim().length() < 5) {
            map.put("status", HospitalStatus.REGISTRATION_ERROR_EMPTY_DESCRIPTION);
            return map;
          }
        }

        if (field.getName().equals("services")) {
          if (ReflectionUtils.getField(field, updatedLaboratory) != null &&
              ReflectionUtils.getField(field, updatedLaboratory).toString().trim().length() < 5) {
            map.put("status", HospitalStatus.REGISTRATION_ERROR_EMPTY_SERVICES);
            return map;
          }
        }

        Object laboratoryFromDbValue = ReflectionUtils.getField(field, updatedLaboratory);
        if (laboratoryFromDbValue != null) {
          ReflectionUtils.setField(field, laboratoryFromDb, laboratoryFromDbValue);
        }
      }
      map.put("status", HospitalStatus.EDIT_PROFILE_SUCCESS);
      map.put("laboratory", laboratoryRepository.save(laboratoryFromDb));
    }
    return map;
  }

  @Transactional
  public Optional<Laboratory> deleteLaboratory(Long id) {
    Optional<Laboratory> laboratory = laboratoryRepository.findById(id);
    laboratory.ifPresent(laboratory1 -> laboratoryRepository.delete(laboratory1.getId()));
    return laboratory;
  }
}

