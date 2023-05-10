package med.voll.med.domain.appointments;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.med.domain.doctor.Specialty;

import java.time.LocalDateTime;

public record AppointmentSchedulingData(Long idDoctor,

                                        @NotNull Long idPatient,

                                        @NotNull @Future LocalDateTime date,

                                        Specialty specialty) {
}




