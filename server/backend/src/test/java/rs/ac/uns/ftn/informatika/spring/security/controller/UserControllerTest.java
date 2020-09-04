package rs.ac.uns.ftn.informatika.spring.security.controller;


import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static rs.ac.uns.ftn.informatika.spring.securit.service.constants.UserConstants.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import rs.ac.uns.ftn.informatika.spring.securit.service.constants.UserConstants;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	private static final String URL_PREFIX = "/api/user";
	private static final String API_URL_PREFIX = "/api";


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
	public void testGetAllUsers() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/all")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$", hasSize(DB_COUNT)))
		.andExpect(jsonPath("$.[*].id").value(hasItem(UserConstants.DB_ID.intValue())))
		.andExpect(jsonPath("$.[*].firstName").value(hasItem(DB_FIRST_NAME)))
		.andExpect(jsonPath("$.[*].lastName").value(hasItem(DB_LAST_NAME)));
	}

	

	@Test
	public void testGetUser() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/" + UserConstants.DB_ID)).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$.id").value(UserConstants.DB_ID.intValue()))
		.andExpect(jsonPath("$.firstName").value(DB_FIRST_NAME))
		.andExpect(jsonPath("$.lastName").value(DB_LAST_NAME));
	}
	@Test
	public void testGetUsersJMBG() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/pacient/jmbg")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$",hasSize(DB_COUNT_PACIENT)));
	}

	@Test
	public void testGetDoctors() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/doctor")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$",hasSize(DB_COUNT_DOCTORS)));
	}
	@Test
	public void testGetNurces() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "/nurces")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$",hasSize(DB_COUNT_NURCES)));
	}
	
	
	@Test
	public void testGetDoctorTimetable() throws Exception {
		mockMvc.perform(get(API_URL_PREFIX + "/doctor/" + DB_ID.toString() +"/timetable")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$",hasSize(DB_COUNT_DOCTORS_SCHEDULE_TIME)));
	}
	
	
}