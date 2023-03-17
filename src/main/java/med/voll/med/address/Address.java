/**
 * @author :Gustavo
 * Date :16/03/2023
 * Time :17:03
 * Project Name :med
 **/
package med.voll.med.address;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public Address(AddressData addressData) {
        this.streetAddress = addressData.street_address();
        this.neighborhood = addressData.neighborhood();
        this.cep = addressData.cep();
        this.city = addressData.city();
        this.uf = addressData.uf();
        this.number = addressData.number();
        this.complement = addressData.complement();
    }
}
