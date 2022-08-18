package guru.springframework.msscbreweryservice.web.mappers;

import org.mapstruct.Mapper;

import guru.springframework.msscbreweryservice.domain.Beer;
import guru.springframework.msscbreweryservice.web.model.BeerDto;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
	
	Beer beerDtoToBeer(BeerDto dto);
	BeerDto beerToBeerDto(Beer beer);

}
