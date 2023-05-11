package med.voll.med.domain.appointments.validations.appointments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.med.domain.appointments.AppointmentRepository;
import med.voll.med.domain.appointments.AppointmentSchedulingData;
import med.voll.med.infra.exception.ValidationException;

@Component
public class DoctorWithAnotherAppointmentAtSameTimetableValidator {

    @Autowired
    AppointmentRepository appointmentRepository;

    public void validate(AppointmentSchedulingData data) {

        var doctorWithAnotherAppointmentAtSameTimetableValidator = appointmentRepository.existsByDoctorIdAndDateAndCancellationReasonIsNull(data.idDoctor(), data.date());
        if (doctorWithAnotherAppointmentAtSameTimetableValidator) {
            throw new ValidationException("Doctor with another appointment at same timetable");
        }

    }

}
