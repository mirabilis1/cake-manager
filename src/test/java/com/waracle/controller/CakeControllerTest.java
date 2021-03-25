package com.waracle.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.BaseTestCase;
import com.waracle.service.CakeService;

@RunWith(SpringRunner.class)
@WebMvcTest(CakeController.class)
public class CakeControllerTest extends BaseTestCase {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CakeService cakeService;
	
	@Test
	public void getAllCakes() throws Exception {
		given(cakeService.getAllCakes()).willReturn(samples);

		mockMvc.perform(MockMvcRequestBuilders.get(END_POINT))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$[0].title").value("Carrot Cake"))
				.andExpect(jsonPath("$[0].desc").value("Carrot flavoured cake"))
				.andExpect(jsonPath("$[0].image").value("https://images.immediate.co.uk/production/volatile/sites/30/2020/08/carrot-cake-f1b3d0c.jpg"));
	}
	
	@Test
	public void createCake() throws Exception {	
		String requestBody = new ObjectMapper().writeValueAsString(sample);

		given(cakeService.createCake(sample)).willReturn(samples.get(2));

		mockMvc.perform(MockMvcRequestBuilders.post(END_POINT)
				.headers(httpHeaders)
				.content(requestBody)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.cakeId").value(3L))
				.andExpect(jsonPath("$.title").value("Red Velvet"))
				.andExpect(jsonPath("$.desc").value("Red velvet cake is a classic chocolate layer cake with a striking reddish-brown sponge"))
				.andExpect(jsonPath("$.image").value("https://images.immediate.co.uk/production/volatile/sites/30/2020/08/red-velvet-cake-513716c.jpg"));

	}

}
