package med.voll.med.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import med.voll.med.domain.address.Address;
import med.voll.med.domain.address.AddressData;
import med.voll.med.domain.doctor.Doctor;
import med.voll.med.domain.doctor.DoctorDetailingData;
import med.voll.med.domain.doctor.DoctorRegistrationData;
import med.voll.med.domain.doctor.DoctorRepository;
import med.voll.med.domain.doctor.Specialty;

@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureJsonTesters
public class DoctorControllerTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private JacksonTester<DoctorRegistrationData> doctorRegistrationDataJson;

    @Autowired
    private JacksonTester<DoctorDetailingData> doctorDetalingDataJson;

    @MockBean
    private DoctorRepository doctorRepository;

    @Test
    @DisplayName("Should return http code 400 when the informations are not valid")
    @WithMockUser
    void registration_scenario1() throws Exception {

        var response = mock.perform(post("/doctors")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return http code 200 when the informations are valid")
    @WithMockUser
    void registration_scenario2() throws Exception {

        var registrationData = new DoctorRegistrationData(
                "Doctor",
                "doctor@med.voll",
                "12345",
                "2177713415155151",
                Specialty.ORTOPEDIA,
                addressData());

        when(doctorRepository.save(any())).thenReturn(new Doctor(registrationData));

        var response = mock.perform(post("/doctors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(doctorRegistrationDataJson.write(registrationData).getJson()))
                .andReturn().getResponse();

        var detailingData = new DoctorDetailingData(
                null,
                registrationData.name(),
                registrationData.email(),
                registrationData.phone(),
                registrationData.crm(),
                registrationData.specialty(),
                new Address(registrationData.address()));

        var expectedJson = doctorDetalingDataJson.write(detailingData).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        // assertThat(response.getContentAsString()).isEqualTo(expectedJson); CORRIGIR ESSA MERDA
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
