package med.voll.med.domain.appointments;

import java.time.LocalDateTime;

import med.voll.med.domain.doctor.Specialty;

public record AppointmentDetailingData(Long id, Long idDoctor, Long idPatient, LocalDateTime date) {

    public AppointmentDetailingData(Appointment appointment) {
        this(appointment.getId(), appointment.getDoctor().getId(), appointment.getPatient().getId(), appointment.getDate());
    }


}
