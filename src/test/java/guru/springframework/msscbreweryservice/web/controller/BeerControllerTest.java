package guru.springframework.msscbreweryservice.web.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import guru.springframework.msscbreweryservice.bootstrap.BeerLoader;
import guru.springframework.msscbreweryservice.web.model.BeerDto;
import guru.springframework.msscbreweryservice.web.model.BeerStyle;



@WebMvcTest(BeerController.class)
public class BeerControllerTest {
	
	 @Autowired
	    MockMvc mockMvc;

	    @Autowired
	    ObjectMapper objectMapper;

//	    @MockBean
//	    BeerService beerService;

	    @Test
	    void getBeerById() throws Exception {

	        //given(beerService.getById(any(), anyBoolean())).willReturn(getValidBeerDto());

	        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());

	    }

	    @Test
	    void saveNewBeer() throws Exception {

	        BeerDto beerDto = getValidBeerDto();
	        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

	       // given(beerService.saveNewBeer(any())).willReturn(getValidBeerDto());

	        mockMvc.perform(post("/api/v1/beer/")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(beerDtoJson))
	                .andExpect(status().isCreated());
	    }

	    @Test
	    void updateBeerById() throws Exception {
	       // given(beerService.updateBeer(any(), any())).willReturn(getValidBeerDto());

	        BeerDto beerDto = getValidBeerDto();
	        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

	        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString())
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(beerDtoJson))
	                .andExpect(status().isNoContent());
	    }

	    BeerDto getValidBeerDto(){
	        return BeerDto.builder()
	                .beerName("My Beer")
	                .beerStyle(BeerStyle.ALE)
	                .price(new BigDecimal("2.99"))
	                .upc(BeerLoader.BEER_1_UPC)
	                .build();
	    }
}
