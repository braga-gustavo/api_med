package med.voll.med.domain.appointments.validations.appointments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.med.domain.appointments.AppointmentSchedulingData;
import med.voll.med.domain.doctor.DoctorRepository;
import med.voll.med.infra.exception.ValidationException;

@Component
public class ActiveDoctorValidator implements AppointmentSchedulingValidator {

    @Autowired
    DoctorRepository doctorRepository;

    public void validate(AppointmentSchedulingData data) {
        var doctor = data.idDoctor();

        if (doctor == null) {
            return;
        }

        var doctorIsActive = doctorRepository.findActiveByID(doctor);
        if (!doctorIsActive) {
            throw new ValidationException("Doctor is not active");
        }

    }
}
