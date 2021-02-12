package com.sl.ms.inventorymanagement;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(path="inventory", rel="inventory")
public interface InventoryRepository extends CrudRepository<Inventory, Long>{

}
