package imedevo.controller;
import imedevo.httpStatuses.NoSuchClinicException;
import imedevo.model.Clinic;
import imedevo.service.ClinicService;
import imedevo.util.ErrorBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/clinics")
public class ClinicController {


    @Autowired
    private ClinicService clinicService;

    @GetMapping("/getall")
    public List<Clinic> getAllClinics() {
        return clinicService.getAll();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getClinicById(@PathVariable Long id) {
        Optional<Clinic> mayBeClinic = clinicService.getById(id);

        return mayBeClinic.map(Object.class::cast)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest()
                        .body(new ErrorBody("there is no clinic with ID = " + id)));
    }

    @PostMapping("/createclinic")
    public Map<String, Object> createClinic(@RequestBody Clinic clinic) {
        return clinicService.save(clinic);
    }

    @PutMapping("/{id}")
    public Map<String, Object> updateClinic(@PathVariable Long id,@RequestBody Clinic clinic) {
        clinic.setId(id);
        return clinicService.updateClinic(clinic);
    }

    @DeleteMapping("/{id}")
    public void deleteClinic(@PathVariable Long id){
        clinicService.delete(id)
                     .orElseThrow(NoSuchClinicException::new);
    }


}
