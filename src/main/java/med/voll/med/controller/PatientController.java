/**
 * @author :Gustavo
 * Date :14/03/2023
 * Time :18:20
 * Project Name :med
 **/
package med.voll.med.controller;

import med.voll.med.doctor.DoctorRegistrationData;
import med.voll.med.patient.PatientRegistrationData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/patients")
public class PatientController {

    @PostMapping
    public void patientRegistration(@RequestBody PatientRegistrationData patientRegistrationData){
        System.out.println(patientRegistrationData);
    }
}
