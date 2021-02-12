package com.sl.ms.inventorymanagement;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="SL_PRODUCTS")
public class Product {

  @Id
  private Long id;
  private String name;
  private Double price;
  private Integer quantity;

  /**
   * @param id
   * @param name
   * @param price
   * @param quantity
   */
  public Product(Long id, String name, Double price, Integer quantity) {
  	super();
  	this.id = id;
  	this.name = name;
  	this.price = price;
  	this.quantity = quantity;
  }
  public Product() {
}
/**
 * @return the id
 */
public Long getId() {
	return id;
}

/**
 * @param id the id to set
 */
public void setId(Long id) {
	this.id = id;
}
/**
 * @return the name
 */
public String getName() {
	return name;
}
/**
 * @param name the name to set
 */
public void setName(String name) {
	this.name = name;
}
/**
 * @return the price
 */
public Double getPrice() {
	return price;
}
/**
 * @param price the price to set
 */
public void setPrice(Double price) {
	this.price = price;
}
/**
 * @return the quantity
 */
public Integer getQuantity() {
	return quantity;
}
/**
 * @param quantity the quantity to set
 */
public void setQuantity(Integer quantity) {
	this.quantity = quantity;
}
  
}
