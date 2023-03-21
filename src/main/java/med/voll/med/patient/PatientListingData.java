/**
 * @author :Gustavo
 * Date :21/03/2023
 * Time :09:36
 * Project Name :med
 **/
package med.voll.med.patient;

public record PatientListingData(String name, String email, String cpf) {

    public PatientListingData(Patient patient) {
        this(patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
