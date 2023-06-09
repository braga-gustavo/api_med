/**
 * @author :Gustavo
 * Date :14/03/2023
 * Time :18:20
 * Project Name :med
 **/
package med.voll.med.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.med.domain.doctor.Doctor;
import med.voll.med.domain.doctor.DoctorDataToUpdate;
import med.voll.med.domain.doctor.DoctorDetailingData;
import med.voll.med.domain.doctor.DoctorListingData;
import med.voll.med.domain.doctor.DoctorRegistrationData;
import med.voll.med.domain.doctor.DoctorRepository;

@RestController
@RequestMapping(value = "/doctors")
@SecurityRequirement(name = "bearer-key")
public class DoctorController {

    @Autowired
    DoctorRepository doctorRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ResponseEntity doctorRegistration(@RequestBody @Valid DoctorRegistrationData doctorRegistrationData,
            UriComponentsBuilder uriComponentsBuilder) {
        var doctor = new Doctor(doctorRegistrationData);
        doctorRepository.save(doctor);
        var uri = uriComponentsBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DoctorListingData(doctor));

    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    @Transactional
    public ResponseEntity<Page<DoctorListingData>> doctorListing(
            @PageableDefault(size = 10, sort = { "name" }) Pageable pageable) {
        var page = doctorRepository.findAllByActiveTrue(pageable).map(DoctorListingData::new);
        return ResponseEntity.ok(page);

    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Transactional
    public ResponseEntity updateDoctor(@RequestBody @Valid DoctorDataToUpdate doctorDataToUpdate) {
        var doctor = doctorRepository.getReferenceById(doctorDataToUpdate.id());
        doctor.updateDoctorData(doctorDataToUpdate);
        return ResponseEntity.ok(new DoctorDetailingData(doctor));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Transactional
    public ResponseEntity deleteDoctor(@PathVariable Long id) {
        var doctor = doctorRepository.getReferenceById(id);
        doctor.inactivate();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity detailDoctor(@PathVariable Long id) {
        var doctor = doctorRepository.getReferenceById(id);
        return ResponseEntity.ok(new DoctorDetailingData(doctor));
    }
}
