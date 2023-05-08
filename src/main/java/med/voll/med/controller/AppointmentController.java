package med.voll.med.controller;

import jakarta.validation.Valid;
import med.voll.med.domain.appointments.AppointmentDetailingData;
import med.voll.med.domain.appointments.AppointmentSchedulingData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    public ResponseEntity schedule(@RequestBody @Valid AppointmentSchedulingData data) {
        return ResponseEntity.ok(new AppointmentDetailingData(null, null, null));

    }
}
