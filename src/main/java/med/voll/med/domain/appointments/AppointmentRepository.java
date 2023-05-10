/**
 * @author :Gustavo
 * Date :08/05/2023
 * Time :18:07
 * Project Name :med
 **/
package med.voll.med.domain.appointments;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Boolean existsByDoctorIdAndDateAndCancellationReasonIsNull(Long idDoctor, LocalDateTime date);

    Boolean existsByPatientIdAndDateBetween(Long idPatient, LocalDateTime firstTimetable, LocalDateTime lastTimetable);
}
