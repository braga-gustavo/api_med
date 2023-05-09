package med.voll.med.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.med.domain.appointments.AppointmentDetailingData;
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
        schedule.scheduler(data);
        return ResponseEntity.ok(new AppointmentDetailingData(null, null, null, null));

    }
}
