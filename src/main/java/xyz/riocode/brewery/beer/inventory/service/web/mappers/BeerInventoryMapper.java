package xyz.riocode.brewery.beer.inventory.service.web.mappers;

import org.mapstruct.Mapper;
import xyz.riocode.brewery.beer.inventory.service.domain.BeerInventory;
import xyz.riocode.brewery.beer.inventory.service.web.model.BeerInventoryDto;

@Mapper(uses = {DateMapper.class})
public interface BeerInventoryMapper {

    BeerInventory beerInventoryDtoToBeerInventory(BeerInventoryDto beerInventoryDTO);

    BeerInventoryDto beerInventoryToBeerInventoryDto(BeerInventory beerInventory);
}
