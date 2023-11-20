package xyz.riocode.brewery.beer.inventory.service.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.riocode.brewery.beer.inventory.service.domain.BeerInventory;
import xyz.riocode.brewery.beer.inventory.service.repositories.BeerInventoryRepository;
import xyz.riocode.brewery.common.model.BeerOrderDto;
import xyz.riocode.brewery.common.model.BeerOrderLineDto;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
@Service
public class AllocationServiceImpl implements AllocationService {

    private final BeerInventoryRepository beerInventoryRepository;

    @Override
    public Boolean allocateOrder(BeerOrderDto beerOrderDto) {
        AtomicInteger totalOrdered = new AtomicInteger();
        AtomicInteger totalAllocated = new AtomicInteger();

        beerOrderDto.getBeerOrderLines().forEach(beerOrderLine -> {
            if (((beerOrderLine.getOrderQuantity() != null ? beerOrderLine.getOrderQuantity() : 0)
                - (beerOrderLine.getAllocatedQuantity() != null ? beerOrderLine.getAllocatedQuantity() : 0)) > 0) {
                allocateBeerOrderLine(beerOrderLine);
            }
            totalOrdered.set(totalOrdered.get() + beerOrderLine.getOrderQuantity());
            totalAllocated.set(totalAllocated.get() + (beerOrderLine.getAllocatedQuantity() != null ? beerOrderLine.getAllocatedQuantity() : 0));
        });
        return totalOrdered.get() == totalAllocated.get();
    }

    @Override
    public void deallocateOrder(BeerOrderDto beerOrderDto) {
        beerOrderDto.getBeerOrderLines().forEach(orderLineDto -> {
            BeerInventory inventory = BeerInventory.builder()
                    .beerId(orderLineDto.getBeerId())
                    .upc(orderLineDto.getUpc())
                    .quantityOnHand(orderLineDto.getAllocatedQuantity())
                    .build();

            beerInventoryRepository.save(inventory);
        });
    }

    private void allocateBeerOrderLine(BeerOrderLineDto beerOrderLine) {
        List<BeerInventory> beerInventoryList = beerInventoryRepository.findAllByUpc(beerOrderLine.getUpc());

        beerInventoryList.forEach(beerInventory -> {
            int inventory = beerInventory.getQuantityOnHand() != null ? beerInventory.getQuantityOnHand() : 0;
            int ordered = beerOrderLine.getOrderQuantity() != null ? beerOrderLine.getOrderQuantity() : 0;
            int allocated = beerOrderLine.getAllocatedQuantity() != null ? beerOrderLine.getAllocatedQuantity() : 0;
            int toAllocated = ordered - allocated;

            if (inventory >= toAllocated) {
                inventory = inventory - toAllocated;
                beerOrderLine.setAllocatedQuantity(toAllocated);
                beerInventory.setQuantityOnHand(inventory);
                beerInventoryRepository.save(beerInventory);
            } else if (inventory > 0) {
                beerOrderLine.setAllocatedQuantity(allocated + inventory);
                beerInventory.setQuantityOnHand(0);
            }

            if (beerInventory.getQuantityOnHand() == 0) {
                beerInventoryRepository.delete(beerInventory);
            }
        });
    }
}
