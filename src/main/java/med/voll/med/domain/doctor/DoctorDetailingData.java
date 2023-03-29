/**
 * @author :Gustavo
 * Date :24/03/2023
 * Time :21:53
 * Project Name :med
 **/
package med.voll.med.domain.doctor;

import med.voll.med.domain.address.Address;

public record DoctorDetailingData(Long id, String name, String email, String crm, String phone,
                                  Specialty specialty, Address address) {


    public DoctorDetailingData(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getPhone(),
                doctor.getSpecialty(), doctor.getAddress());

    }
}
