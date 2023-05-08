/**
 * @author :Gustavo
 * Date :08/05/2023
 * Time :18:07
 * Project Name :med
 **/
package med.voll.med.domain.appointments;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentsRepository extends JpaRepository<Appointment, Long> {
}
