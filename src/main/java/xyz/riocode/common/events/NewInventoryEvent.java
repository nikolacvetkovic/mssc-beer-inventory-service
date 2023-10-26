package xyz.riocode.common.events;

import lombok.NoArgsConstructor;
import xyz.riocode.common.model.BeerDto;

@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent{

    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
