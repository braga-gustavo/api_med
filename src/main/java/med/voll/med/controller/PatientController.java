/**
 * @author :Gustavo
 * Date :14/03/2023
 * Time :18:20
 * Project Name :med
 **/
package med.voll.med.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.med.domain.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/patients")
public class PatientController {

    @Autowired
    PatientRepository patientRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ResponseEntity patientRegistration(@RequestBody @Valid PatientRegistrationData patientRegistrationData,
                                              UriComponentsBuilder uriComponentsBuilder) {
        var patient = new Patient(patientRegistrationData);
        patientRepository.save(patient);
        var uri = uriComponentsBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new PatientListingData(patient));

    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    @Transactional
    public Page<PatientListingData> patientListing(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return patientRepository.findAllByActiveTrue(pageable).map(PatientListingData::new);

    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Transactional
    public void updatePatient(@RequestBody @Valid PatientDataToUpdate patientDataToUpdate) {
        var patient = patientRepository.getReferenceById(patientDataToUpdate.id());
        patient.updatePatientData(patientDataToUpdate);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Transactional
    public void deletePatient(@PathVariable Long id) {
        var patient = patientRepository.getReferenceById(id);
        patient.inactivate();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity detailPatient(@PathVariable Long id) {
        var patient = patientRepository.getReferenceById(id);
        return ResponseEntity.ok(new PatientListingData(patient));
    }


}
