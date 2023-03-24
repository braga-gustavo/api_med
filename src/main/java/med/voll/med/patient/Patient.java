/**
 * @author :Gustavo
 * Date :17/03/2023
 * Time :13:43
 * Project Name :med
 **/
package med.voll.med.patient;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.med.address.Address;

import java.io.Serializable;

@Table(name = "patients")
@Entity(name = "patients")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Patient implements Serializable {

    private static final long seriaVerisonUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;
    private String phone;
    private String cpf;

    private Boolean active;

    @Embedded
    private Address address;

    public Patient(PatientRegistrationData patientRegistrationData) {
        this.active = true;
        this.name = patientRegistrationData.name();
        this.email = patientRegistrationData.email();
        this.phone = patientRegistrationData.phone();
        this.cpf = patientRegistrationData.cpf();
        this.address = new Address(patientRegistrationData.address());

    }

    public void updatePatientData(PatientDataToUpdate patientDataToUpdate) {
        if (patientDataToUpdate.name() != null)
            this.name = patientDataToUpdate.name();

        if (patientDataToUpdate.phone() != null)
            this.phone = patientDataToUpdate.phone();

        if (patientDataToUpdate.address() != null)
            address.updateData(patientDataToUpdate.address());
    }

    public void inactivate() {
        this.active = false;
    }
}
