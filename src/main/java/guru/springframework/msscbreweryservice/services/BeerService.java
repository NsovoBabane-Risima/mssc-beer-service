package guru.springframework.msscbreweryservice.services;

import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;

import guru.springframework.msscbreweryservice.web.model.BeerDto;

public interface BeerService {

	BeerDto getBeerById (@NotNull UUID beerId) throws NotFoundException;

	BeerDto saveNewBeer(@Valid BeerDto beerDto) throws NotFoundException;

	BeerDto updateBeerById(@NotNull UUID beerId, @Valid BeerDto beerDto) throws NotFoundException;

}
