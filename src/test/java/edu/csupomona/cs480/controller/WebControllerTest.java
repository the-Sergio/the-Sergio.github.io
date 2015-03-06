package edu.csupomona.cs480.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import edu.csupomona.cs480.App;

/**
 * 
 * @author Roberto Rodriguez
 * Assignment 6
 * 
 * This class tests if the server is running by calling WebController.healthCheck()
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class WebControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	
	String uid = null;

	@Before
	public void setUp() throws Exception {
		mockMvc = webAppContextSetup(wac).build();
		MvcResult result = this.mockMvc.perform(get("/cs480/lunchbox/getUID")).andReturn();
		uid = result.getResponse().getContentAsString();
	}

	@Test
	public void serverRunningTest() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/cs480/ping")).andReturn();
		assertEquals("OK RUNNING", result.getResponse().getContentAsString());
	}
	
	@Test
	public void getUIDTest() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/cs480/lunchbox/getUID")).andReturn();
		String uid = result.getResponse().getContentAsString();
		assertNotNull(uid);	
	}
	
	@Test 
	public void getEmptyLunchBoxTest() throws Exception {
		MvcResult result = this.mockMvc.perform(get("/cs480/lunchbox/" + uid)).andReturn();
		assertTrue(result.getResponse().getContentLength() == 0);
	}
	

}
