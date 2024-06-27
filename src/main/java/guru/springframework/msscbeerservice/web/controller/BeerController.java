package guru.springframework.msscbeerservice.web.controller;

import java.time.OffsetDateTime;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.brewery.model.BeerDto;
import guru.springframework.brewery.model.BeerPagedList;
import guru.springframework.msscbeerservice.domain.BeerStyle;
import guru.springframework.msscbeerservice.services.BeerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

	private final Integer DEFAULT_PAGE_NUMBER = 0;
	private final Integer DEFAULT_PAGE_SIZE = 25;
	private final BeerService beerService;

	@GetMapping(produces = "Application/Json", path = "beer")
	public ResponseEntity<BeerPagedList> listBeers(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "beerName", required = false) String beerName,
			@RequestParam(value = "beerStyle", required = false) BeerStyle beerStyle,
			@RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand) {

		if (showInventoryOnHand == null) {
			showInventoryOnHand = false;
		}

		if (pageNumber == null || pageNumber < 0) {
			pageNumber = DEFAULT_PAGE_NUMBER;
		}

		if (pageSize == null || pageSize < 1) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
		BeerPagedList beerList = beerService.getBeerList(beerName, beerStyle, PageRequest.of(pageNumber, pageSize),
				showInventoryOnHand);
		return new ResponseEntity<>(beerList, HttpStatus.OK);
		

	}

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeerById(@NotNull @PathVariable("beerId") UUID beerId, @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand) throws NotFoundException {

		if (showInventoryOnHand == null) {
            showInventoryOnHand = false;
        }
		
		return new ResponseEntity<>(beerService.getBeerById(beerId, showInventoryOnHand), HttpStatus.OK);

//    	return new ResponseEntity<BeerDto>(BeerDto.builder()
//    			.beerName("Malt")
//    			.beerStyle(BeerStyle.LAGER)
//    			.createdDate(OffsetDateTime.now())
//    			.build(), HttpStatus.OK);

	}
	
	@GetMapping("beerUpc/{upc}")
	public ResponseEntity<BeerDto> getBeerByUpc(@PathVariable("upc") String upc, @RequestParam(value = "showInventoryOnHand", required = false)Boolean showInventoryOnHand){
		
		return new ResponseEntity<BeerDto>(beerService.findBeerByUpc(upc,showInventoryOnHand ), HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<BeerDto> saveNewBeer(@Valid @RequestBody BeerDto beerDto) throws NotFoundException {

		return new ResponseEntity<BeerDto>(beerService.saveNewBeer(beerDto), HttpStatus.CREATED);

	}

	@PutMapping("/{beerId}")
	public ResponseEntity<BeerDto> updateBeerById(@NotNull @PathVariable("beerId") UUID beerId,
			@Valid @RequestBody BeerDto beerDto) throws NotFoundException {

		return new ResponseEntity<BeerDto>(beerService.updateBeerById(beerId, beerDto), HttpStatus.NO_CONTENT);
	}
}
