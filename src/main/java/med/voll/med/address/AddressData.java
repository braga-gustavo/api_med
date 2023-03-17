/**
 * @author :Gustavo
 * Date :14/03/2023
 * Time :19:37
 * Project Name :med
 **/
package med.voll.med.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressData(

        @NotBlank
        String streetAddress,

        @NotBlank
        String neighborhood,

        @NotBlank @Pattern(regexp = "\\d(8)")
        String cep,

        @NotBlank
        String city,

        @NotBlank
        String uf,
        String number,
        String complement
) {


}
