package com.team9.OrderMS.Utility;

import java.io.Serializable;
import java.util.Objects;

public class CompoundKey implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String buyerId;
	protected String productId;
	public CompoundKey() {
		super();
	}
	
	
	public CompoundKey(String buyerId, String productId) {
		super();
		this.buyerId = buyerId;
		this.productId = productId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(buyerId, productId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompoundKey other = (CompoundKey) obj;
		return Objects.equals(buyerId, other.buyerId) && Objects.equals(productId, other.productId);
	}
	
	

}
