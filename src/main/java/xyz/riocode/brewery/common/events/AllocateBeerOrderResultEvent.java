package xyz.riocode.brewery.common.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import xyz.riocode.brewery.common.model.BeerOrderDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllocateBeerOrderResultEvent {
    private BeerOrderDto beerOrderDto;
    private Boolean allocationError;
    private Boolean inventoryPending;
}
