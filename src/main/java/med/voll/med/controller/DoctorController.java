/**
 * @author :Gustavo
 * Date :14/03/2023
 * Time :18:20
 * Project Name :med
 **/
package med.voll.med.controller;

import jakarta.validation.Valid;
import med.voll.med.doctor.DoctorRegistrationData;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/doctors")
public class DoctorController {


    @PostMapping
    public void doctorRegistration(@RequestBody @Valid DoctorRegistrationData doctorRegistrationData){

    }
}
