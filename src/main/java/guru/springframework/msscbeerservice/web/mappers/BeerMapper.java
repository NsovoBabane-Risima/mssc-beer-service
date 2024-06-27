package guru.springframework.msscbeerservice.web.mappers;

import org.mapstruct.Mapper;

import guru.springframework.brewery.model.BeerDto;
import guru.springframework.msscbeerservice.domain.Beer;

@Mapper(uses = { DateMapper.class })
public interface BeerMapper {

	Beer beerDtoToBeer(BeerDto dto);

	BeerDto beerToBeerDto(Beer beer);

	BeerDto beerToBeerDtoWithInventory(Beer beer);
}
