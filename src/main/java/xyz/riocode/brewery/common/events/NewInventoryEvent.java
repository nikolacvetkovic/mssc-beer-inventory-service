package xyz.riocode.brewery.common.events;

import lombok.NoArgsConstructor;
import xyz.riocode.brewery.common.model.BeerDto;

@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent{

    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
