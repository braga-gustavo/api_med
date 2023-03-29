/**
 * @author :Gustavo
 * Date :16/03/2023
 * Time :17:08
 * Project Name :med
 **/
package med.voll.med.domain.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByActiveTrue(Pageable pageable);
}
