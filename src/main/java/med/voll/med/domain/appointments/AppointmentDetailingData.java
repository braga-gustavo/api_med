package med.voll.med.domain.appointments;

import java.time.LocalDateTime;

import med.voll.med.domain.doctor.Specialty;

public record AppointmentDetailingData(Long idDoctor, Long idPatient, LocalDateTime date, Specialty specialty){
}
