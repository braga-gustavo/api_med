/**
 * @author :Gustavo
 * Date :16/03/2023
 * Time :16:56
 * Project Name :med
 **/
package med.voll.med.domain.doctor;

import java.io.Serializable;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.med.domain.address.Address;

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
