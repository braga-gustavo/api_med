/**
 * @author :Gustavo
 * Date :16/03/2023
 * Time :17:08
 * Project Name :med
 **/
package med.voll.med.doctor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepsitory extends JpaRepository<Doctor, Long> {
}
