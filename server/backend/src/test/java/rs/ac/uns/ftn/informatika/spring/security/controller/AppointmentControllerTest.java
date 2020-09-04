package rs.ac.uns.ftn.informatika.spring.security.controller;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static rs.ac.uns.ftn.informatika.spring.securit.service.constants.UserConstants.*;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import rs.ac.uns.ftn.informatika.spring.security.TestUtil;
import rs.ac.uns.ftn.informatika.spring.security.dto.AppointmentDTO;
import rs.ac.uns.ftn.informatika.spring.security.model.Appointment;
import rs.ac.uns.ftn.informatika.spring.security.model.Room;
import rs.ac.uns.ftn.informatika.spring.security.model.SchedulerTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppointmentControllerTest {

	private static final String URL_PREFIX = "/api/appointment";

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype());
	
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testGetDoctorsAppointmnets() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/visits/" +DB_ID)).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$", hasSize(DB_COUNT_DOCTORS_APPOINTMENTS)));	
	}
	
//	@Test
//	public void testGetAppointmnetsByRoom() throws Exception {
//		mockMvc.perform(get(URL_PREFIX + "/room/" +DB_ROOM_ID)).andExpect(status().isOk())
//		.andExpect(content().contentType(contentType))
//		.andExpect(jsonPath("$", hasSize(DB_COUNT_ROOM_APPOINTMENTS)));	
//	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testSaveAppointmnet() throws Exception {
		AppointmentDTO appointment = new AppointmentDTO();
		appointment.setDate(DB_APPOINTMENT_DATE);
		appointment.setDoctorId(DB_ID);
		appointment.setPacientId(DB_ID_REFERENCED);
		appointment.setRoom(DB_ROOM_ID);
		appointment.setBegining("08:00");
		appointment.setEnding("08:30");
		String json = TestUtil.json(appointment);
		this.mockMvc.perform(post(URL_PREFIX + "/room/new")
				.contentType(contentType)
				.content(json))
		.andExpect(status().isOk());
	}
}
