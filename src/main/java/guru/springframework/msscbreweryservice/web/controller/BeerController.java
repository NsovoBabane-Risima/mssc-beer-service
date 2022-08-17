package guru.springframework.msscbreweryservice.web.controller;

import java.time.OffsetDateTime;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.msscbreweryservice.web.model.BeerDto;
import guru.springframework.msscbreweryservice.web.model.BeerStyle;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
    @GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeerById( @NotNull @PathVariable("beerId") UUID beerId){
  	
    	return new ResponseEntity<BeerDto>(BeerDto.builder()
    			.beerName("Malt")
    			.beerStyle(BeerStyle.LAGER)
    			.createdDate(OffsetDateTime.now())
    			.build(), HttpStatus.OK);
		
	}
    
    @PostMapping()
    public ResponseEntity saveNewBeer(@Valid @RequestBody BeerDto beerDto) {
		return new ResponseEntity(HttpStatus.CREATED);
    	
    }
    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById( @NotNull @PathVariable("beerId") UUID beerId,@Valid @RequestBody BeerDto beerDto) {
    	return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
