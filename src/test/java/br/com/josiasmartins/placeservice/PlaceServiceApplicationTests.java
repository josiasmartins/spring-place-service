package br.com.josiasmartins.placeservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.josiasmartins.placeservice.api.PlaceRequest;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) 
class PlaceServiceApplicationTests { // teste de integração

	@Autowired
	WebTestClient webTestClient;

	@Test
	public void testCreatePlaceSuccess() {
		String name = "Valid Name";
		String city = "Valid City";
		String state = "Valid State";
		String slug = "valid-name";
		
		webTestClient
			.post()
			.uri("/places")
			.bodyValue(
				new PlaceRequest(name, city, state)
			)
			.exchange() // efeatua a chamada para api 
			.expectBody()
				.jsonPath("name").isEqualTo(name)
				.jsonPath("state").isEqualTo(state)
				.jsonPath("slug").isEqualTo(slug)
				.jsonPath("createdAt").isNotEmpty()
				.jsonPath("updatedAt").isNotEmpty();
	}

	@Test
	public void testCreatePlaceFailure() {
		String name  = "";
		String city = "Valid City";
		String state = "";

		webTestClient
			.post()
			.uri("/places")
			.bodyValue(
				new PlaceRequest(name, city, state)
			)
			.exchange() // efeatua a chamada para api 
			.expectStatus().isBadRequest();
	}	

}
