/**
 * @author :Gustavo
 * Date :21/03/2023
 * Time :09:04
 * Project Name :med
 **/
package med.voll.med.doctor;

public record DoctorListingData(String name, String email, String crm, Specialty specialty) {

    public DoctorListingData(Doctor doctor) {
        this(doctor.getName(),
                doctor.getEmail(),
                doctor.getCrm(),
                doctor.getSpecialty()
        );
    }
}
