package med.voll.med.domain.appointments;

import java.time.LocalDateTime;

public record AppointmentDetailingData(Long idDoctor, Long idPatient, LocalDateTime date){
}
