package com.sl.ms.inventorymanagement;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductWebServices {
  private static Logger logger = LoggerFactory.getLogger(ProductWebServices.class);
  ProductRepository productRepo;

  public ProductWebServices(ProductRepository productRepo) {
    super();
    this.productRepo = productRepo;
  }

  @GetMapping("/products")
  public Iterable<Product> getProducts() {
    logger.info("Inside GET products");
    return productRepo.findAll();
  }

  @GetMapping("/products/{id}") public Product getbyId(@PathVariable long id) {
    logger.info("Inside GET products by ID:" + id);
    return productRepo.findById(id).get(); }


  @PostMapping(value = "/products/{id}", consumes = "application/json", produces = "application/json")
  public Product saveById(@PathVariable long id, @RequestBody Product p) {
    p.setId(id);
    logger.info("Inside POST products by id:" + id);
    return productRepo.save(p);
  }

  @PostMapping(value = "/products", consumes = "application/json", produces = "application/json")
  public Iterable<Product> saveAll(@RequestBody Iterable<Product> p) {
    logger.info("Inside POST products");
    return productRepo.saveAll(p);
  }

  @PutMapping(value = "/products/{id}", consumes = "application/json", produces = "application/json")
  public Product updateById(@PathVariable long id, @RequestBody Product p) {
    p.setId(id);
    logger.info("Inside PUT products by id:" + id);
    return productRepo.save(p);
  }

  @DeleteMapping(value = "/products/{id}")
  public void deleteById(@PathVariable Long id) {

    logger.info("Inside delete products by id:" + id);
    Product p = productRepo.findById(id).get();
    p.setQuantity(0);
    productRepo.save(p);
  }

  @GetMapping("/supported_products")
  public HashMap<Long, String> getSupportedProducts() {
    logger.info("Inside /supported_products ");
    HashMap<Long, String> uniqueMap = new HashMap<Long, String>();

    productRepo.findAll().forEach(p -> {
      uniqueMap.put(p.getId(), p.getName());
    });
    return uniqueMap;
  }
}
