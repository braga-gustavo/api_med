/**
 * @author :Gustavo
 * Date :16/03/2023
 * Time :17:08
 * Project Name :med
 **/
package med.voll.med.domain.doctor;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByActiveTrue(Pageable pageable);

    @Query("""
            select d from Doctor d
            where d.active = 1
            and
            d.specialty = :specialty
            and 
            d.id not in(
                select a.doctor from Appointment a
                where
                a.date = :date
            )
            order by rand()
            limit 1 
            """)
    Doctor chooseRandomAvailableDoctorOnDate(Specialty specialty, LocalDateTime date);
}
