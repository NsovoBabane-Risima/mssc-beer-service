package guru.springframework.msscbreweryservice.web.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;



@WebMvcTest(BeerController.class)
public class BeerControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	@Test
	public void getBeerById() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/v1/beer"+UUID.randomUUID().toString())
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	@Test
	public void saveNewBeer() {
		
	}
	@Test
	public void updateBeerById() {
		
	}
}
