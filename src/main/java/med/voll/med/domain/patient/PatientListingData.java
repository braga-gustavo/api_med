/**
 * @author :Gustavo
 * Date :21/03/2023
 * Time :09:36
 * Project Name :med
 **/
package med.voll.med.domain.patient;

public record PatientListingData(Long id, String name, String email, String cpf) {

    public PatientListingData(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
