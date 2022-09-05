package guru.springframework.msscbeerservice.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.repository.PagingAndSortingRepository;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.domain.BeerStyle;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID>{
	
	Page<Beer> findAllBeerByName(String name, Pageable pageable);
	Page<Beer> findAllByBeerStyle(BeerStyle beerStyle, Pageable pageable);
	
	Page<Beer> finadAllByBeerNameAndBeerStyle(String name, BeerStyle beerStyle, Pageable pageable);
	Beer findBeerByUpc(String upc);

}
