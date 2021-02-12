package com.sl.ms.inventorymanagement;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(path="products", rel="products")
public interface ProductRepository extends CrudRepository<Product, Long>{

  @Cacheable("Products")
  Iterable<Product> findAll();
  
  @Cacheable(value="product", key="#id")
  Optional<Product> findById(Long id);
}
