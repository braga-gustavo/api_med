/**
 * @author :Gustavo
 * Date :16/03/2023
 * Time :16:56
 * Project Name :med
 **/
package med.voll.med.doctor;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.med.address.Address;
import med.voll.med.address.AddressData;

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

    @NotBlank
    String name;

    @NotBlank @Email
    String email;

    @NotBlank
    String crm;

    @Enumerated(EnumType.STRING)
    Especialty specialty;

    @Embedded
    Address address;

}
