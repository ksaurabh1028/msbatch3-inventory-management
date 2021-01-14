package com.sl.ms.inventorymanagement;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(path="products", rel="products")
public interface ProductRepository extends CrudRepository<Product, Long>{

}
