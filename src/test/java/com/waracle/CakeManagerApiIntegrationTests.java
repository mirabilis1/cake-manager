package com.waracle;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.waracle.dto.CakeDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CakeManagerApiIntegrationTests extends BaseTestCase {

	@Autowired
	private TestRestTemplate testRestTemplate;

	/**
	 * Integration test for RESTful GET Endpoint to get all cakes
	 */
	@Test
	public void firstTest() {
		ParameterizedTypeReference<List<CakeDTO>> responseType = new ParameterizedTypeReference<List<CakeDTO>>() {
		};
		ResponseEntity<List<CakeDTO>> response = testRestTemplate.exchange(END_POINT, HttpMethod.GET, null,
				responseType);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		List<CakeDTO> intersection = samples.stream().filter(response.getBody()::contains).collect(Collectors.toList());

		// In-memory database will have data inserted during system start-up.
		// First two items in the samples list are same as those retrieved from
		// in-memory database during call to this GET Endpoint.
		assertThat(intersection.size() == 2).isTrue();
		// Third item in the samples list is not yet present in in-memory database
		assertThat(intersection.contains(samples.get(2))).isFalse();
	}

	/**
	 * Integration test for RESTful POST Endpoint to create a new cake
	 * 
	 * @param <T>
	 */
	@Test
	public <T> void secondTest() {		
		HttpEntity<CakeDTO> httpEntity = new HttpEntity<CakeDTO>(sample, httpHeaders);

		ResponseEntity<CakeDTO> response = testRestTemplate.exchange(END_POINT, HttpMethod.POST, httpEntity,
				CakeDTO.class);

		// Third item in the samples list is same as the sample inserted to in-memory
		// database during call to this POST Endpoint.
		assertThat(response.getBody()).isEqualTo(samples.get(2));
	}

}
