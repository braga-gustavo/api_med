package med.voll.med.domain.appointments;

import med.voll.med.domain.doctor.Doctor;
import med.voll.med.domain.doctor.DoctorRepository;
import med.voll.med.domain.patient.PatientRepository;
import med.voll.med.infra.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentSchedule {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    public void scheduler(AppointmentSchedulingData data) {

        if (!patientRepository.existsById(data.idPatient())) {
            throw new ValidationException("Patient id does not exist");
        }

        if (data.idDoctor() != null && !doctorRepository.existsById(data.idDoctor())) {
            throw new ValidationException("Doctor id does not exist");
        }

        var patient = patientRepository.findById(data.idPatient()).get();
        var doctor = doctorRepository.findById(data.idDoctor()).get();
        var appointment = new Appointment(null, doctor, patient, data.date());
        appointmentRepository.save(appointment);

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
}
