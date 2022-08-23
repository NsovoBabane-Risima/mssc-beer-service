package guru.springframework.msscbreweryservice.services;

import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import guru.springframework.msscbreweryservice.domain.Beer;
import guru.springframework.msscbreweryservice.repositories.BeerRepository;
import guru.springframework.msscbreweryservice.web.mappers.BeerMapper;
import guru.springframework.msscbreweryservice.web.model.BeerDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

//@AllArgsConstructor
@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {
	private final BeerRepository beerRepository;
	private final BeerMapper beerMapper;

	public BeerDto getBeerById(@NotNull UUID beerId) throws NotFoundException {

		return beerMapper.beerToBeerDto(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
	}

	@Override
	public BeerDto saveNewBeer(@Valid BeerDto beerDto) {
		Beer savedBeer = beerRepository.save(beerMapper.beerDtoToBeer(beerDto));
		return beerMapper.beerToBeerDto(savedBeer);
	}

	public BeerDto updateBeerById(@NotNull UUID beerId, @Valid BeerDto beerDto)  throws NotFoundException{
		
		Beer updateBeer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
		updateBeer.setBeerName(beerDto.getBeerName());
		updateBeer.setBeerStyle(beerDto.getBeerStyle());
		updateBeer.setPrice(beerDto.getPrice());
		updateBeer.setUpc(beerDto.getUpc());
		return beerMapper.beerToBeerDto(beerRepository.save(updateBeer));
		 
	}

}
