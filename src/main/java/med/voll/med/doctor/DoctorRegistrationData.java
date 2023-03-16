/**
 * @author :Gustavo
 * Date :14/03/2023
 * Time :19:33
 * Project Name :med
 **/
package med.voll.med.doctor;

import med.voll.med.address.AddressData;

public record DoctorRegistrationData(String name, String email, String crm,
                                     Especialty specialty, AddressData address) {
}
