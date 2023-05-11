package med.voll.med.domain.appointments.validations.appointments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.med.domain.appointments.AppointmentSchedulingData;
import med.voll.med.domain.patient.PatientRepository;
import med.voll.med.infra.exception.ValidationException;

@Component
public class ActivePatientValidator implements AppointmentSchedulingValidator {

    @Autowired
    PatientRepository patientRepository;

    public void validate(AppointmentSchedulingData data) {
        var patient = data.idPatient();

        if (patient == null) {
            return;
        }

        var patientIsActive = patientRepository.findActiveById(patient);
        if (!patientIsActive) {
            throw new ValidationException("Patient is not active");
        }

    }
}
