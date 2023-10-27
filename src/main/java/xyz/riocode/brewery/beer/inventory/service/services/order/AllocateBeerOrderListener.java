package xyz.riocode.brewery.beer.inventory.service.services.order;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import xyz.riocode.brewery.beer.inventory.service.config.JmsConfig;
import xyz.riocode.brewery.beer.inventory.service.services.AllocationService;
import xyz.riocode.brewery.common.events.AllocateBeerOrderEvent;
import xyz.riocode.brewery.common.events.AllocateBeerOrderResultEvent;

@RequiredArgsConstructor
@Component
public class AllocateBeerOrderListener {

    private final AllocationService allocationService;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.ALLOCATE_BEER_ORDER_REQ_QUEUE)
    public void listen(AllocateBeerOrderEvent event) {
        AllocateBeerOrderResultEvent.AllocateBeerOrderResultEventBuilder builder
                = AllocateBeerOrderResultEvent.builder().beerOrderDto(event.getBeerOrderDto());

        try {
            Boolean isAllocationSuccessful = allocationService.allocateOrder(event.getBeerOrderDto());
            builder.inventoryPending(!isAllocationSuccessful);
        } catch (Exception e) {
            builder.allocationError(true);
        }
        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_BEER_ORDER_RES_QUEUE, builder.build());
    }

}
