package med.voll.med.domain.doctor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import med.voll.med.domain.address.AddressData;
import med.voll.med.domain.appointments.Appointment;
import med.voll.med.domain.patient.Patient;
import med.voll.med.domain.patient.PatientRegistrationData;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Should return null when the only registered doctor is unavailable at date")
    void testChooseRandomAvailableDoctorOnDateScenario1() {
        //given or arrange
        var nextMonday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        var doctor = registerDoctor("Doctor", "doctor@voll.med", "1234", Specialty.CARDIOLOGIA);
        var patient = registerPatient("Patient", "patient@email.com", "123456");        
        scheduleAppointment(doctor, patient, nextMonday);

        //when or act 
        var availableDoctor = doctorRepository.chooseRandomAvailableDoctorOnDate(Specialty.CARDIOLOGIA, nextMonday);
        
        //then or assert
        Assertions.assertThat(availableDoctor).isNull();

    }

    @Test
    @DisplayName("Should return doctor when he's available on the date")
    void testChooseRandomAvailableDoctorOnDateScenario2() {
        var nextMonday = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        var doctor = registerDoctor("Doctor", "doctor@voll.med", "1234", Specialty.CARDIOLOGIA);

        var availableDoctor = doctorRepository.chooseRandomAvailableDoctorOnDate(Specialty.CARDIOLOGIA, nextMonday);
        Assertions.assertThat(availableDoctor).isEqualTo(doctor);

    }

    private void scheduleAppointment(Doctor doctor, Patient patient, LocalDateTime date) {
        entityManager.persist(new Appointment(null, doctor, patient, date, null));

    }

    private Doctor registerDoctor(String name, String email, String crm, Specialty specialty) {
        var doctor = new Doctor(doctorData(name, email, crm, specialty));
        entityManager.persist(doctor);
        return doctor;
    }

    private Patient registerPatient(String name, String email, String cpf) {
       var patient = new Patient(patientData(name, email, cpf));
        entityManager.persist(patient);
        return patient;
    }

    private DoctorRegistrationData doctorData(String name, String email, String crm, Specialty specialty) {
        return new DoctorRegistrationData(name, email,  crm, "123456",specialty, addressData());
    }

    private PatientRegistrationData patientData(String name, String email, String cpf) {
        return new PatientRegistrationData(name, email, "12313151", cpf, addressData());
    }

    private AddressData addressData() {
        return new AddressData(
                "rua tal",
                "bairro",
                "00000000",
                "Nova iguaçu",
                "RJ",
                null,
                null);
    }

}
