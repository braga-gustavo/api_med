package med.voll.med.domain.appointments.validations.appointments;

import java.time.DayOfWeek;

import org.springframework.stereotype.Component;

import med.voll.med.domain.appointments.AppointmentSchedulingData;
import med.voll.med.infra.exception.ValidationException;

@Component
public class ClinicOperatingHoursValidator implements AppointmentSchedulingValidator {
    public void validate(AppointmentSchedulingData data) {
        var appointmentDate = data.date();

        var sunday = appointmentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeClinicOpening = appointmentDate.getHour() < 7;
        var afterClinicClosing = appointmentDate.getHour() > 18;

        if (sunday || beforeClinicOpening || afterClinicClosing) {
            throw new ValidationException("Appointment not in clinic working hours");
        }
    }
}
