package guru.springframework.msscbeerservice.services;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.domain.BeerStyle;
import guru.springframework.msscbeerservice.exceptions.NotFoundException;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.web.mappers.BeerMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerPagedList;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@AllArgsConstructor

@Slf4j
@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {
	private final BeerRepository beerRepository;
	private final BeerMapper beerMapper;

	@Cacheable(cacheNames = "beerCache", key = "#beerId", condition = "#showInventoryOnHand==false")
	public BeerDto getBeerById(@NotNull UUID beerId, boolean showInventoryOnHand) throws NotFoundException {
		
		if(showInventoryOnHand) {
			return beerMapper.beerToBeerDtoWithInventory(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
		}else {

		return beerMapper.beerToBeerDto(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
		}
	}

	@Override
	public BeerDto saveNewBeer(@Valid BeerDto beerDto) {
		Beer savedBeer = beerRepository.save(beerMapper.beerDtoToBeer(beerDto));
		return beerMapper.beerToBeerDto(savedBeer);
	}

	public BeerDto updateBeerById(@NotNull UUID beerId, @Valid BeerDto beerDto) throws NotFoundException {

		Beer updateBeer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
		updateBeer.setBeerName(beerDto.getBeerName());
		updateBeer.setBeerStyle(beerDto.getBeerStyle());
		updateBeer.setPrice(beerDto.getPrice());
		updateBeer.setUpc(beerDto.getUpc());
		return beerMapper.beerToBeerDto(beerRepository.save(updateBeer));

	}
    
	@Cacheable(cacheNames = "beerlistCache", condition = "#showInventoryOnHand == false")
	 @Override
	public BeerPagedList getBeerList(String beerName, BeerStyle beerStyle, PageRequest pageRequest,
			Boolean showInventoryOnHand) {
		Page<Beer> pagedBeer;
		BeerPagedList beerPagedList = null;

		if (!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
			
			pagedBeer = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
		} else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
			pagedBeer = beerRepository.findAllByBeerName(beerName, pageRequest);
		} else if (!StringUtils.isEmpty(beerStyle) && StringUtils.isEmpty(beerName)) {
			pagedBeer = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
			
			log.info("pagedBeer : " + pagedBeer.getContent().get(0).getBeerName());
		} else {
			pagedBeer = beerRepository.findAll(pageRequest);
		}

		if (showInventoryOnHand) {

			beerPagedList = new BeerPagedList(
					pagedBeer.getContent().stream().map(beer -> beerMapper.beerToBeerDtoWithInventory(beer)).collect(Collectors.toList()),
					PageRequest.of(pagedBeer.getPageable().getPageNumber(), pagedBeer.getPageable().getPageSize()),
					pagedBeer.getTotalElements());
		} else {
			
			log.info(pagedBeer.getContent().stream().map(beerMapper::beerToBeerDto).collect(Collectors.toList()).toString());
			
			beerPagedList = new BeerPagedList(
					pagedBeer.getContent().stream().map(beerMapper::beerToBeerDto).collect(Collectors.toList()),
					PageRequest.of(pagedBeer.getPageable().getPageNumber(), pagedBeer.getPageable().getPageSize()),
					pagedBeer.getTotalElements());

		}

		return beerPagedList;
	}

	
    @Cacheable(cacheNames = "beerByUpc", condition = "#showInventoryOnHand == false")
	@Override
	public BeerDto findBeerByUpc(String upc, boolean showInventoryOnHand) {
		if(showInventoryOnHand) {
			return beerMapper.beerToBeerDtoWithInventory(beerRepository.findBeerByUpc(upc));
		}
		else {
		return beerMapper.beerToBeerDto(beerRepository.findBeerByUpc(upc));
		}
	}

}
