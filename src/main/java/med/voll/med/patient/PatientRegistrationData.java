/**
 * @author :Gustavo
 * Date :14/03/2023
 * Time :21:14
 * Project Name :med
 **/
package med.voll.med.patient;

import med.voll.med.address.AddressData;

public record PatientRegistrationData(String name, String email, String phone, String cpf, AddressData address) {
}
