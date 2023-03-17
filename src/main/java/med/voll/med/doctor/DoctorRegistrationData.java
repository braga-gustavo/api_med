/**
 * @author :Gustavo
 * Date :14/03/2023
 * Time :19:33
 * Project Name :med
 **/
package med.voll.med.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.med.address.AddressData;

public record DoctorRegistrationData(

        @NotBlank
        String name,

        @NotBlank @Email
        String email,

        @NotBlank @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotBlank
        String phone,

        @NotNull
        Specialty specialty,

        @NotNull @Valid
        AddressData address) {
}
