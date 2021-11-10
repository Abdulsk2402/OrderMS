package com.team9.OrderMS.DTO;

public class OrderPlacedDTO {
	
	private String orderId;
	private String buyerId;
	private Integer rewardPoints;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public Integer getRewardPoints() {
		return rewardPoints;
	}
	public void setRewardPoints(Integer rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	@Override
	public String toString() {
		return "OrderPlacedDTO [orderId=" + orderId + ", buyerId=" + buyerId + ", rewardPoints=" + rewardPoints + "]";
	}
	
	

}
