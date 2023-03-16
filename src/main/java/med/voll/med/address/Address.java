/**
 * @author :Gustavo
 * Date :16/03/2023
 * Time :17:03
 * Project Name :med
 **/
package med.voll.med.address;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.service.annotation.GetExchange;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Address {

    @NotBlank
    String streetAddress;

    @NotBlank
    String neighborhood;

    @NotBlank
    String cep;

    @NotBlank
    String city;

    @NotBlank
    String uf;

    String number;
    String complement;
}
