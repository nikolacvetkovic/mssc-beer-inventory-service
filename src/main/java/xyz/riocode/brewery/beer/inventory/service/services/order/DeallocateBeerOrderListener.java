package xyz.riocode.brewery.beer.inventory.service.services.order;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import xyz.riocode.brewery.beer.inventory.service.config.JmsConfig;
import xyz.riocode.brewery.beer.inventory.service.services.AllocationService;
import xyz.riocode.brewery.common.events.DeallocateBeerOrderEvent;

@RequiredArgsConstructor
@Component
public class DeallocateBeerOrderListener {

    private final AllocationService allocationService;

    @JmsListener(destination = JmsConfig.DEALLOCATE_BEER_ORDER_REQ_QUEUE)
    public void listen(DeallocateBeerOrderEvent event) {
        allocationService.deallocateOrder(event.getBeerOrderDto());
    }
}
