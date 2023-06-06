package med.voll.med.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDateTime;

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

import med.voll.med.domain.appointments.AppointmentDetailingData;
import med.voll.med.domain.appointments.AppointmentSchedule;
import med.voll.med.domain.appointments.AppointmentSchedulingData;
import med.voll.med.domain.doctor.Specialty;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class AppointmentControllerTest {

    @Autowired
    private MockMvc mock;

    @Autowired
    private JacksonTester<AppointmentSchedulingData> appointmentSchedulingdataJson;

    @Autowired
    private JacksonTester<AppointmentDetailingData> appointmentDetalingdataJson;

    @MockBean
    private AppointmentSchedule appointmentSchedule;

    @Test
    @DisplayName("Should return http code 400 when the informations are not valid")
    @WithMockUser
    void testScheduleScenario1() throws Exception {
        var response = mock.perform(post("/appointments")).andReturn().getResponse();
        
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return http code 200 when the informations are valid")
    @WithMockUser
    void testScheduleScenario2() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var specialty = Specialty.ORTOPEDIA;

        var detailingData = new AppointmentDetailingData(null, 2l, 1l, date);
        when(appointmentSchedule.appoint(any())).thenReturn(detailingData);

        var response = mock.perform(post("/appointments")
                .contentType(MediaType.APPLICATION_JSON).content(appointmentSchedulingdataJson
                .write(new AppointmentSchedulingData(2l, 1l, date, specialty)).getJson())).andReturn().getResponse();
                

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var expectedJson = appointmentDetalingdataJson.write(detailingData).getJson();

        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }
}
