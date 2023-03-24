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

    public void updateData(AddressData addressData) {

        if (addressData.street_address() != null)
            this.streetAddress = addressData.street_address();

        if (addressData.street_address() != null)
            this.neighborhood = addressData.neighborhood();

        if (addressData.street_address() != null)
            this.cep = addressData.cep();

        if (addressData.street_address() != null)
            this.city = addressData.city();

        if (addressData.street_address() != null)
            this.uf = addressData.uf();

        if (addressData.street_address() != null)
            this.number = addressData.number();

        if (addressData.street_address() != null)
            this.complement = addressData.complement();

    }
}
