/**
 * @author :Gustavo
 * Date :10/05/2023
 * Time :17:16
 * Project Name :med
 **/
package med.voll.med.domain.appointments.validations.cancellations;

import med.voll.med.domain.appointments.AppointmentRepository;
import med.voll.med.infra.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("AdvanceTimeValidatorCancellation")
public class AdvanceTimeValidator implements AppointmentCancellingValidator {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Override
    public void validate(AppointmentCancellationData data) {
        var appointment = appointmentRepository.getReferenceById(data.idAppointment());
        var now = LocalDateTime.now();
        var differenceInHours = Duration.between(now, appointment.getDate()).toHours();

        if (differenceInHours < 24) {
            throw new ValidationException("Appoint can only be cancelled with 24 hours minimum advance time");
        }
    }
}
