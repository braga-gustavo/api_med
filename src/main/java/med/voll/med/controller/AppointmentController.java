package med.voll.med.controller;

import med.voll.med.domain.appointments.validations.cancellations.AppointmentCancellationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.med.domain.appointments.AppointmentSchedule;
import med.voll.med.domain.appointments.AppointmentSchedulingData;

@RestController
@RequestMapping(value = "/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentSchedule schedule;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public ResponseEntity schedule(@RequestBody @Valid AppointmentSchedulingData data) {
        var dto = schedule.appoint(data);
        return ResponseEntity.ok(dto);

    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity cancel(@RequestBody @Valid AppointmentCancellationData data) {
        schedule.cancel(data);
        return ResponseEntity.noContent().build();

    }

}
