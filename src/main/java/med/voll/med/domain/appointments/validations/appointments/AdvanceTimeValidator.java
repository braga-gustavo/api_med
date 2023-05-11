package med.voll.med.domain.appointments.validations.appointments;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.med.domain.appointments.AppointmentSchedulingData;
import med.voll.med.infra.exception.ValidationException;

@Component("AdvanceTimeValidatorSchedule")
public class AdvanceTimeValidator implements AppointmentSchedulingValidator {

    public void validate(AppointmentSchedulingData data) {
        var date = data.date();
        var now = LocalDateTime.now();

        var differenceInMintutes = Duration.between(now, date).toMinutes(); // Duration calcula a duração de um periodo.

        if (differenceInMintutes < 30) {
            throw new ValidationException("Appointment needs to be scheduled with at least 30 minutes of advance");
        }

    }

}
