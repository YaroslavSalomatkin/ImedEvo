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
import imedevo.model.Diagnostic;
import imedevo.model.Geoposition;
import imedevo.repository.DiagnosticRepository;
import javax.transaction.Transactional;

@Service
public class DiagnosticService {

  @Autowired
  private DiagnosticRepository diagnosticRepository;

  @Autowired
  private GeocodingService geocoding;

  public List<Diagnostic> getAll() {
    return (List<Diagnostic>) diagnosticRepository.findAll();
  }

  public Diagnostic getById(long id) throws NoSuchClinicException {
    Diagnostic diagnostic = diagnosticRepository.findOne(id);
    if (diagnostic == null) {
      throw new NoSuchClinicException();
    }
    return diagnostic;
  }

  @Transactional
  public Map<String, Object> saveDiagnostic(Diagnostic diagnostic) {
    Map<String, Object> map = new HashMap<>();

    if (diagnostic.getLogo() == null) {
      map.put("status", HospitalStatus.REGISTRATION_ERROR_EMPTY_LOGO);
      return map;
    }

    if (diagnostic.getName() == null || diagnostic.getName().trim().length() < 3) {
      map.put("status", HospitalStatus.REGISTRATION_ERROR_EMPTY_NAME);
      return map;
    }

    if (diagnostic.getMedicalLicense() == null) {
      map.put("status", HospitalStatus.REGISTRATION_ERROR_EMPTY_MEDICAL_LECENSE);
      return map;
    }

    if (diagnostic.getAddress() == null || diagnostic.getAddress().trim().length() < 8) {
      map.put("status", HospitalStatus.REGISTRATION_ERROR_EMPTY_ADDRESS);
      return map;
    }

    if (diagnostic.getDescription() == null || diagnostic.getDescription().trim().length() < 5) {
      map.put("status", HospitalStatus.REGISTRATION_ERROR_EMPTY_DESCRIPTION);
      return map;
    }
    Geoposition geoposition = geocoding.getGeopositionByAddress(diagnostic.getAddress());
    diagnostic.setAddress(geoposition.getAddress());
    diagnostic.setLatitude(geoposition.getLat());
    diagnostic.setLongitude(geoposition.getLng());

    diagnostic.setRegistrationDate(LocalDate.now().toString());
    map.put("status", HospitalStatus.REGISTRATION_OK);
    map.put("diagnostic", diagnosticRepository.save(diagnostic));
    return map;
  }

  @Transactional
  public Map<String, Object> updateDiagnostic(Diagnostic updatedDiagnostic) {
    Map<String, Object> map = new HashMap<>();

    if (updatedDiagnostic.getId() == null) {
      map.put("status", HospitalStatus.EDIT_PROFILE_ERROR);
      return map;
    }

    if (updatedDiagnostic.getAddress() != null) {
      Geoposition geoposition = geocoding.getGeopositionByAddress(updatedDiagnostic.getAddress());
      updatedDiagnostic.setAddress(geoposition.getAddress());
      updatedDiagnostic.setLatitude(geoposition.getLat());
      updatedDiagnostic.setLongitude(geoposition.getLng());
    }

    Diagnostic diagnosticFromDb = diagnosticRepository.findOne(updatedDiagnostic.getId());
    if (diagnosticFromDb == null) {
      map.put("status", HospitalStatus.NOT_FOUND);
    } else {
      Field[] fields = updatedDiagnostic.getClass().getDeclaredFields();
      AccessibleObject.setAccessible(fields, true);
      for (Field field : fields) {
        if (field.getName().equals("id") || field.getName().equals("rating") ||
            field.getName().equals("registrationDate") || field.getName().equals("latitude") ||
            field.getName().equals("longitude")) {
          continue;
        }

        if (field.getName().equals("name")) {
          if (ReflectionUtils.getField(field, updatedDiagnostic) != null &&
              ReflectionUtils.getField(field, updatedDiagnostic).toString().trim().length() < 3) {
            map.put("status", HospitalStatus.REGISTRATION_ERROR_EMPTY_NAME);
            return map;
          }
        }

        if (field.getName().equals("medicalLicense")) {
          if (ReflectionUtils.getField(field, updatedDiagnostic) != null &&
              ReflectionUtils.getField(field, updatedDiagnostic).toString().trim().length() < 5) {
            map.put("status", HospitalStatus.REGISTRATION_ERROR_EMPTY_MEDICAL_LECENSE);
            return map;
          }
        }

        if (field.getName().equals("address")) {
          if (ReflectionUtils.getField(field, updatedDiagnostic) != null &&
              ReflectionUtils.getField(field, updatedDiagnostic).toString().trim().length() < 5) {
            map.put("status", HospitalStatus.REGISTRATION_ERROR_EMPTY_ADDRESS);
            return map;
          }
        }

        if (field.getName().equals("description")) {
          if (ReflectionUtils.getField(field, updatedDiagnostic) != null &&
              ReflectionUtils.getField(field, updatedDiagnostic).toString().trim().length() < 5) {
            map.put("status", HospitalStatus.REGISTRATION_ERROR_EMPTY_DESCRIPTION);
            return map;
          }
        }

        if (field.getName().equals("services")) {
          if (ReflectionUtils.getField(field, updatedDiagnostic) != null &&
              ReflectionUtils.getField(field, updatedDiagnostic).toString().trim().length() < 5) {
            map.put("status", HospitalStatus.REGISTRATION_ERROR_EMPTY_SERVICES);
            return map;
          }
        }

        Object diagnosticFromDbValue = ReflectionUtils.getField(field, updatedDiagnostic);
        if (diagnosticFromDbValue != null) {
          ReflectionUtils.setField(field, diagnosticFromDb, diagnosticFromDbValue);
        }
      }
      map.put("status", HospitalStatus.EDIT_PROFILE_SUCCESS);
      map.put("diagnostic", diagnosticRepository.save(diagnosticFromDb));
    }
    return map;
  }

  @Transactional
  public Optional<Diagnostic> deleteDiagnostic(Long id) {
    Optional<Diagnostic> diagnostic = diagnosticRepository.findById(id);
    diagnostic.ifPresent(diagnostic1 -> diagnosticRepository.delete(diagnostic1.getId()));
    return diagnostic;
  }
}

