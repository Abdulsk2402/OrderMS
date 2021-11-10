package com.team9.OrderMS.Service;

import java.util.List;

import com.team9.OrderMS.DTO.CartDTO;
import com.team9.OrderMS.DTO.OrderDTO;
import com.team9.OrderMS.DTO.OrderPlacedDTO;
import com.team9.OrderMS.DTO.ProductDTO;
import com.team9.OrderMS.Exception.OrderException;

public interface OrderService {
	
	public List<OrderDTO> viewAllOrders() throws OrderException;

	public OrderPlacedDTO placeOrder(List<ProductDTO> productList, List<CartDTO> cartList, OrderDTO order) throws OrderException;

	public List<OrderDTO> viewOrdersByBuyer(String buyerId)throws OrderException;

	public OrderDTO viewOrder(String orderId) throws OrderException;

	public String reOrder(String buyerId, String orderId) throws OrderException;



}
