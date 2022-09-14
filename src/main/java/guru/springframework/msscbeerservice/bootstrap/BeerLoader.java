package guru.springframework.msscbeerservice.bootstrap;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.domain.BeerStyle;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class BeerLoader implements CommandLineRunner {

	public static final String BEER_1_UPC = "0631234200036";
	public static final String BEER_2_UPC = "0631234300019";
	public static final String BEER_3_UPC = "0083783375213";

	private final BeerRepository beerRepository;

	@Override
	public void run(String... args) throws Exception {

		if (beerRepository.count() == 0) {
			loadBeerObjects();
		}
	}

	private void loadBeerObjects() {
		Beer b1 = Beer.builder().beerName("Mango Bobs").beerStyle(BeerStyle.IPA).quantityOnHand(12).quantityToBrew(200)
				.price(new BigDecimal("12.95")).upc(BEER_1_UPC).build();

		Beer b2 = Beer.builder().beerName("Galaxy Cat").beerStyle(BeerStyle.PALE_ALE).quantityOnHand(12)
				.quantityToBrew(200).price(new BigDecimal("12.95")).upc(BEER_2_UPC).build();

		Beer b3 = Beer.builder().beerName("Pinball Porter").beerStyle(BeerStyle.PALE_ALE).quantityOnHand(12)
				.quantityToBrew(200).price(new BigDecimal("12.95")).upc(BEER_3_UPC).build();

		if (beerRepository.findBeerByUpc(b1.getUpc()) == null) {
			beerRepository.save(b1);
		}
		if (beerRepository.findBeerByUpc(b2.getUpc()) == null) {
			beerRepository.save(b2);
		}
		if (beerRepository.findBeerByUpc(b3.getUpc()) == null) {
			beerRepository.save(b3);
		}

	}
}