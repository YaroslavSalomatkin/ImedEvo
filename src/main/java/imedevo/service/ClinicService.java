package imedevo.service;


import imedevo.model.Clinic;
import imedevo.repository.ClinicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    public List<Clinic> getAll() {
        return clinicRepository.findAll();
    }

    public Optional<Clinic> getById(Long id) {
        return clinicRepository.findById(id);
    }

    public Clinic save(Clinic clinic) {
        return clinicRepository.save(clinic);
    }


    public Optional<Clinic> delete(Long id) {
        Optional<Clinic> mayBeClinic = clinicRepository.findById(id);
        mayBeClinic.ifPresent(clinic -> clinicRepository.delete(clinic.getId()));
        return mayBeClinic;
    }


}
