package guru.springframework.msscbeerservice.web.mappers;

import org.mapstruct.Mapper;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.web.model.BeerDto;

@Mapper(uses = { DateMapper.class })
public interface BeerMapper {

	Beer beerDtoToBeer(BeerDto dto);

	BeerDto beerToBeerDto(Beer beer);

	BeerDto beerToBeerDtoWithInventory(Beer beer);
}
