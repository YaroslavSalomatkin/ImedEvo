package imedevo.controller;
import imedevo.httpStatuses.DocStatus;
import imedevo.httpStatuses.NoSuchClinicException;
import imedevo.model.Clinic;
import imedevo.service.ClinicService;
import imedevo.util.ErrorBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
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
    public ResponseEntity<Void> createClinic(@RequestBody Clinic clinic) {

        Clinic saved = clinicService.save(clinic);

        return ResponseEntity.created(URI.create("/createclinic/" + saved.getId())).build();
    }
    @PutMapping("/{id}")
    public void updateClinic(@PathVariable Long id, @RequestBody Clinic clinic) {
        clinic.setId(id);

        clinicService.save(clinic);
    }

    @DeleteMapping("/{id}")
    public void deleteClinic(@PathVariable Long id){
        clinicService.delete(id)
                     .orElseThrow(NoSuchClinicException::new);
    }


}
