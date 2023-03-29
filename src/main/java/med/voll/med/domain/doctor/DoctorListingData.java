/**
 * @author :Gustavo
 * Date :21/03/2023
 * Time :09:04
 * Project Name :med
 **/
package med.voll.med.domain.doctor;

public record DoctorListingData(Long id, String name, String email, String crm, Specialty specialty) {

    public DoctorListingData(Doctor doctor) {
        this(
                doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getCrm(),
                doctor.getSpecialty()
        );
    }
}
