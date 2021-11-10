package com.team9.OrderMS.Entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.team9.OrderMS.Utility.CompoundKey;

@Entity
@Table(name = "products_ordered")
public class ProductOrderedEntity {
	
	@EmbeddedId
	private CompoundKey compoundKey;
	
	private String sellerId;	
	private Integer quantity;
	public CompoundKey getCompoundKey() {
		return compoundKey;
	}
	public void setCompoundKey(CompoundKey compoundKey) {
		this.compoundKey = compoundKey;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	

}
