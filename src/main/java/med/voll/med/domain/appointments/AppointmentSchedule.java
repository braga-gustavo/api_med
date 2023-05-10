package med.voll.med.domain.appointments;

import med.voll.med.domain.appointments.validations.cancellations.AppointmentCancellationData;
import med.voll.med.domain.appointments.validations.cancellations.AppointmentCancellingValidator;
import med.voll.med.domain.appointments.validations.appointments.AppointmentSchedulingValidator;
import med.voll.med.domain.doctor.Doctor;
import med.voll.med.domain.doctor.DoctorRepository;
import med.voll.med.domain.patient.PatientRepository;
import med.voll.med.infra.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentSchedule {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<AppointmentSchedulingValidator> validators;

    @Autowired
    private List<AppointmentCancellingValidator> cancellingValidators;

    public AppointmentDetailingData appoint(AppointmentSchedulingData data) {
        if (!patientRepository.existsById(data.idPatient())) {
            throw new ValidationException("Patient id does not exist");
        }

        if (data.idDoctor() != null && !doctorRepository.existsById(data.idDoctor())) {
            throw new ValidationException("Doctor id does not exist");
        }

        validators.forEach(v -> v.validate(data));

        var patient = patientRepository.getReferenceById(data.idPatient());
        var doctor = chooseDoctor(data);
        if (doctor == null) {
            throw new ValidationException("There is no doctor available on this date");
        }

        var appointment = new Appointment(null, doctor, patient, data.date(), null);
        appointmentRepository.save(appointment);

        return new AppointmentDetailingData(appointment);

    }

    public Doctor chooseDoctor(AppointmentSchedulingData data) {
        if (data.idDoctor() != null) {
            return doctorRepository.getReferenceById(data.idDoctor());

        }

        if (data.specialty() == null) {
            throw new ValidationException("Specialty is necessary when doctor is not selected");
        }

        return doctorRepository.chooseRandomAvailableDoctorOnDate(data.specialty(), data.date());

    }

    public void cancel(AppointmentCancellationData data) {
        if (!appointmentRepository.existsById(data.idAppointment())) {
            throw new ValidationException("Invalid appointment ID");
        }

        cancellingValidators.forEach(v -> v.validate(data));


        var appointment = appointmentRepository.getReferenceById(data.idAppointment());
        appointment.cancel(data.reason());
    }
}
