package guru.springframework.msscbeerservice.services.inventory;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import guru.springframework.msscbeerservice.services.inventory.model.BeerInventoryDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BeerInventoryServiceRestTemplateImpl implements BeerInventoryService {

	private final String INVENTORY_PATH = "/api/v1/beer/{beerId}/inventory";
	private final RestTemplate restTemplate;
	private final String beerInventoryServiceHost;

	public BeerInventoryServiceRestTemplateImpl(RestTemplateBuilder restTemplate,
			@Value("${sfg.brewery.inventory-host}") String beerInventoryServiceHost,
			@Value("${sfg.brewery.inventory-password}") String inventoryUser,
			@Value("${sfg.brewery.inventory-user}") String password) {
		super();
		this.restTemplate = restTemplate.basicAuthentication(inventoryUser, password).build();
		this.beerInventoryServiceHost = beerInventoryServiceHost;

	}

	@Override
	public Integer getOnhandInventory(UUID beerId) {
		log.info("Calling Inventory Service");

		ResponseEntity<List<BeerInventoryDto>> responseEntity = restTemplate.exchange(
				beerInventoryServiceHost + INVENTORY_PATH, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<BeerInventoryDto>>() {
				}, beerId);

		Integer onHand = Objects.requireNonNull(responseEntity.getBody()).stream()
				.mapToInt(BeerInventoryDto::getQuantityOnHand).sum();

		return onHand;
	}

}
