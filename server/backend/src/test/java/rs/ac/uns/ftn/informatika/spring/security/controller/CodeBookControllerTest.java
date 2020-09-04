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
import static rs.ac.uns.ftn.informatika.spring.securit.service.constants.CodebookConstants.*;
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
import rs.ac.uns.ftn.informatika.spring.security.model.Diagnose;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CodeBookControllerTest {
	private static final String URL_PREFIX = "/api/";

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
	public void testGetDiagnoses() throws Exception {
		mockMvc.perform(get(URL_PREFIX + "codes/diagnoses/all")).andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$", hasSize(DB_DIAGNOSES_COUNT)));
	}
	@Test
	@Transactional
	@Rollback(true)
	public void testSaveDiagnose() throws Exception {
		Diagnose diagnose = new Diagnose();
		diagnose.setCode(DB_DIAGNOSE_CODE);
		diagnose.setName(DB_DIAGNOSE_NAME);
		
			
		String json = TestUtil.json(diagnose);
		this.mockMvc.perform(post(URL_PREFIX + "/codes/diagnose")
				.contentType(contentType)
				.content(json))
		.andExpect(status().isOk());
	}


}
