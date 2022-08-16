package guru.springframework.msscbreweryservice.bootstrap;

import java.math.BigDecimal;import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import guru.springframework.msscbreweryservice.domain.Beer;
import guru.springframework.msscbreweryservice.repositories.BeerRepository;
import guru.springframework.msscbreweryservice.web.model.BeerStyle;

public class BeerLoader implements CommandLineRunner {
	
	@Autowired
	private BeerRepository beerRepository;

	@Override
	public void run(String... args) throws Exception {
		
		loadData();
		
	}

	private void loadData() {
		Beer beer1 = Beer.builder()
				.beerName("Stout")
				.beerStyle(BeerStyle.ALE)
				.price(new BigDecimal(2000))
				.id(UUID.randomUUID())
				.build();
		
		Beer beer2 = Beer.builder()
				.beerName("ALE")
				.beerStyle(BeerStyle.ALE)
				.price(new BigDecimal(2000))
				.id(UUID.randomUUID())
				.build();
		
	}

}
