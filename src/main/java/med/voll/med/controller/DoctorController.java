/**
 * @author :Gustavo
 * Date :14/03/2023
 * Time :18:20
 * Project Name :med
 **/
package med.voll.med.controller;

import jakarta.validation.Valid;
import med.voll.med.doctor.Doctor;
import med.voll.med.doctor.DoctorRegistrationData;
import med.voll.med.doctor.DoctorRepository;
import med.voll.med.doctor.DoctorListingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/doctors")
public class DoctorController {

    @Autowired
    DoctorRepository doctorRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void doctorRegistration(@RequestBody @Valid DoctorRegistrationData doctorRegistrationData) {
        doctorRepository.save(new Doctor(doctorRegistrationData));

    }

    @GetMapping
    public Page<DoctorListingData> doctorListing(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return doctorRepository.findAll(pageable).map(DoctorListingData::new);

    }
}
