/**
 * @author :Gustavo
 * Date :16/03/2023
 * Time :16:56
 * Project Name :med
 **/
package med.voll.med.doctor;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.med.address.Address;

import java.io.Serializable;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String crm;

    private Boolean active;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Address address;

    public Doctor(DoctorRegistrationData doctorRegistrationData) {

        this.active = true;
        this.name = doctorRegistrationData.name();
        this.email = doctorRegistrationData.email();
        this.phone = doctorRegistrationData.phone();
        this.crm = doctorRegistrationData.crm();
        this.specialty = doctorRegistrationData.specialty();
        this.address = new Address(doctorRegistrationData.address());
    }

    public void updateDoctorData(DoctorDataToUpdate doctorDataToUpdate) {
        if (doctorDataToUpdate.name() != null)
            this.name = doctorDataToUpdate.name();

        if (doctorDataToUpdate.phone() != null)
            this.phone = doctorDataToUpdate.phone();

        if (doctorDataToUpdate.address() != null)
            address.updateData(doctorDataToUpdate.address());
    }

    public void inactivate() {
        this.active = false;
    }
}
