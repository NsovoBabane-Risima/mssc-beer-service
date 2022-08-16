package guru.springframework.msscbreweryservice.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import guru.springframework.msscbreweryservice.domain.Beer;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID>{

}
