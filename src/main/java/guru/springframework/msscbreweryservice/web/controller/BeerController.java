package guru.springframework.msscbreweryservice.web.controller;

import java.time.OffsetDateTime;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.msscbreweryservice.services.BeerService;
import guru.springframework.msscbreweryservice.web.model.BeerDto;
import guru.springframework.msscbreweryservice.web.model.BeerStyle;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

	private final BeerService beerService;

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeerById(@NotNull @PathVariable("beerId") UUID beerId) throws NotFoundException {

		return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);

//    	return new ResponseEntity<BeerDto>(BeerDto.builder()
//    			.beerName("Malt")
//    			.beerStyle(BeerStyle.LAGER)
//    			.createdDate(OffsetDateTime.now())
//    			.build(), HttpStatus.OK);

	}

	@PostMapping()
	public ResponseEntity<BeerDto> saveNewBeer(@Valid @RequestBody BeerDto beerDto) throws NotFoundException {

		return new ResponseEntity<BeerDto>(beerService.saveNewBeer(beerDto), HttpStatus.CREATED);

	}

	@PutMapping("/{beerId}")
	public ResponseEntity<BeerDto> updateBeerById(@NotNull @PathVariable("beerId") UUID beerId,
			@Valid @RequestBody BeerDto beerDto) throws NotFoundException {

		return new ResponseEntity<BeerDto>(beerService.updateBeerById(beerId, beerDto),HttpStatus.NO_CONTENT);
	}
}
