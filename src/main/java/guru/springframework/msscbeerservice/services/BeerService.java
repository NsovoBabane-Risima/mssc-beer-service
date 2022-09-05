package guru.springframework.msscbeerservice.services;

import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import guru.springframework.msscbeerservice.domain.BeerStyle;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerPagedList;

public interface BeerService {

	BeerDto getBeerById (@NotNull UUID beerId, boolean showInventoryOnHand) throws NotFoundException;

	BeerDto saveNewBeer(@Valid BeerDto beerDto) throws NotFoundException;

	BeerDto updateBeerById(@NotNull UUID beerId, @Valid BeerDto beerDto) throws NotFoundException;

	BeerPagedList getBeerList(String beerName, BeerStyle beerStyle, PageRequest pageRequest,
			Boolean showInventoryOnHand);
	
	BeerDto findBeerByUpc(String upc, boolean showInventoryOnHand);

}
