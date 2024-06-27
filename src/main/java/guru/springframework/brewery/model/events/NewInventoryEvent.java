package guru.springframework.brewery.model.events;

import guru.springframework.brewery.model.BeerDto;

public class NewInventoryEvent extends BeerEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1767703357372754708L;

	public NewInventoryEvent(BeerDto beerDto) {
		super(beerDto);
	}

}
