/**
 * @author :Gustavo
 * Date :14/03/2023
 * Time :18:20
 * Project Name :med
 **/
package med.voll.med.controller;

import jakarta.validation.Valid;
import med.voll.med.doctor.DoctorRegistrationData;
import med.voll.med.patient.Patient;
import med.voll.med.patient.PatientListingData;
import med.voll.med.patient.PatientRegistrationData;
import med.voll.med.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/patients")
public class PatientController {

    @Autowired
    PatientRepository patientRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void patientRegistration(@RequestBody @Valid PatientRegistrationData patientRegistrationData) {
        patientRepository.save(new Patient(patientRegistrationData));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<PatientListingData> patientListing(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return patientRepository.findAll(pageable).map(PatientListingData::new);
    }
}
