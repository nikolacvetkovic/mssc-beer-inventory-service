package xyz.riocode.brewery.common.events;

import lombok.NoArgsConstructor;
import xyz.riocode.brewery.common.model.BeerDto;

@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent{

    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
