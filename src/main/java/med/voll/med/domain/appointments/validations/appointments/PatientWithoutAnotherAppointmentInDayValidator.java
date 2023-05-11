package med.voll.med.domain.appointments.validations.appointments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.med.domain.appointments.AppointmentRepository;
import med.voll.med.domain.appointments.AppointmentSchedulingData;
import med.voll.med.infra.exception.ValidationException;

@Component
public class PatientWithoutAnotherAppointmentInDayValidator {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate(AppointmentSchedulingData data) {

        var firstTimetable = data.date().withHour(7);
        var lastTimetable = data.date().withHour(18);

        var patientHasAnotherAppointmentInDay = appointmentRepository.existsByPatientIdAndDateBetween(data.idPatient(), firstTimetable, lastTimetable);
        if (patientHasAnotherAppointmentInDay) {
            throw new ValidationException("Patient has another appointment in this day");
        }

    }

}
