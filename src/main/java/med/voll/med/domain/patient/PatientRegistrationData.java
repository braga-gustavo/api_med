/**
 * @author :Gustavo
 * Date :14/03/2023
 * Time :21:14
 * Project Name :med
 **/
package med.voll.med.domain.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.med.domain.address.AddressData;
import org.hibernate.validator.constraints.br.CPF;

public record PatientRegistrationData(

        @NotBlank
        String name,

        @NotBlank @Email
        String email,

        @NotBlank
        String phone,

        @NotBlank @CPF
        String cpf,

        @NotNull @Valid
        AddressData address) {
}
