package med.voll.med.domain.appointments;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record AppointmentSchedulingData(
    Long idDoctor,

    @NotNull
    Long idPatient, 

    @NotNull
    @Future
    LocalDateTime date) {

}
