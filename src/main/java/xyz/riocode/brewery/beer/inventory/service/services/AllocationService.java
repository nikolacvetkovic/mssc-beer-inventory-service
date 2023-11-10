package xyz.riocode.brewery.beer.inventory.service.services;

import xyz.riocode.brewery.common.model.BeerOrderDto;

public interface AllocationService {
    Boolean allocateOrder(BeerOrderDto beerOrderDto);
    void deallocateOrder(BeerOrderDto beerOrderDto);
}
