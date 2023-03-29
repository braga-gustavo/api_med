/**
 * @author :Gustavo
 * Date :21/03/2023
 * Time :16:30
 * Project Name :med
 **/
package med.voll.med.domain.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.med.domain.address.AddressData;

public record PatientDataToUpdate(

        @NotNull
        Long id,

        String name,

        String phone,

        @Valid
        AddressData address) {
}
