package guru.springframework.msscbeerservice.events;

import java.io.Serializable;

import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class BeerEvent implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3738178240496566665L;
	private final BeerDto beerDto;
	
	

}
