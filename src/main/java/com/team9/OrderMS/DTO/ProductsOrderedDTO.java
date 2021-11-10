package com.team9.OrderMS.DTO;

public class ProductsOrderedDTO {
	
	private String buyerId;
	private String sellerId;
	private String productId;
	private Integer quantity;
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "ProductsOrderedDTO [buyerId=" + buyerId + ", sellerId=" + sellerId + ", productId=" + productId
				+ ", quantity=" + quantity + "]";
	}
	
	
	

}
