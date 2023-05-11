/**
 * @author :Gustavo
 * Date :09/05/2023
 * Time :14:13
 * Project Name :med
 **/
package med.voll.med.domain.appointments.validations.cancellations;

import jakarta.validation.constraints.NotNull;

public record AppointmentCancellationData
        (@NotNull Long idAppointment,

         @NotNull CancellationReason reason

        ) {
}
