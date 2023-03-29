/**
 * @author :Gustavo
 * Date :21/03/2023
 * Time :17:47
 * Project Name :med
 **/
package med.voll.med.domain.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.med.domain.address.AddressData;

public record DoctorDataToUpdate(

        @NotNull
        Long id,

        String name,

        String phone,

        @Valid
        AddressData address

) {
}
