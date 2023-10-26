package xyz.riocode.brewery.beer.inventory.service.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import xyz.riocode.brewery.beer.inventory.service.config.JmsConfig;
import xyz.riocode.brewery.beer.inventory.service.domain.BeerInventory;
import xyz.riocode.brewery.beer.inventory.service.repositories.BeerInventoryRepository;
import xyz.riocode.brewery.common.events.NewInventoryEvent;

@Slf4j
@RequiredArgsConstructor
@Service
public class NewInventoryListener {

    private final BeerInventoryRepository beerInventoryRepository;

    @JmsListener(destination = JmsConfig.NEW_INVENTORY_QUEUE)
    public void listen(NewInventoryEvent event) {
        log.debug("Got inventory: " + event.toString());
        beerInventoryRepository.save(BeerInventory.builder()
                .beerId(event.getBeerDto().getId())
                .upc(event.getBeerDto().getUpc())
                .quantityOnHand(event.getBeerDto().getQuantityOnHand())
                .build());
    }
}
