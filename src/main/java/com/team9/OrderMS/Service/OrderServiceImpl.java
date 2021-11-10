package com.team9.OrderMS.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team9.OrderMS.DTO.CartDTO;
import com.team9.OrderMS.DTO.OrderDTO;
import com.team9.OrderMS.DTO.OrderPlacedDTO;
import com.team9.OrderMS.DTO.ProductDTO;
import com.team9.OrderMS.Entity.OrderEntity;
import com.team9.OrderMS.Entity.ProductOrderedEntity;
import com.team9.OrderMS.Exception.OrderException;
import com.team9.OrderMS.Repository.OrderRepository;
import com.team9.OrderMS.Repository.ProductOrderedRepository;
import com.team9.OrderMS.Utility.CompoundKey;
import com.team9.OrderMS.Utility.OrderStatus;
import com.team9.OrderMS.Validator.OrderValidator;

@Service(value = "orderService")
@Transactional
public class OrderServiceImpl implements OrderService{
	
private static int o;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductOrderedRepository prodOrderedRepository;
	
	static {
		o=100;
	}

	@Override
	public List<OrderDTO> viewAllOrders() throws OrderException {
		Iterable<OrderEntity> orders = orderRepository.findAll();
		List<OrderDTO> dtoList = new ArrayList<>();
		orders.forEach(order -> {
			OrderDTO odto = new OrderDTO();
			odto.setOrderId(order.getOrderId());
			odto.setBuyerId(order.getBuyerId());
			odto.setAmount(order.getAmount());
			odto.setAddress(order.getAddress());
			odto.setDate(order.getDate());
			odto.setStatus(order.getStatus());
			dtoList.add(odto);			
		});
		if(dtoList.isEmpty()) throw new OrderException("No orders available");
		return dtoList;
	}

	@Override
	public OrderPlacedDTO placeOrder(List<ProductDTO> productList, List<CartDTO> cartList, OrderDTO orderDTO)
			throws OrderException {
		OrderEntity order = new OrderEntity();
		OrderValidator.validateOrder(orderDTO);
		String id = "O" + o++;
		order.setOrderId(id);
		order.setAddress(orderDTO.getAddress());
		order.setBuyerId(cartList.get(0).getBuyerId());
		order.setDate(LocalDate.now());
		order.setStatus(OrderStatus.ORDER_PLACED.toString());	
		order.setAmount(0f);
		List<ProductOrderedEntity> productsOrdered = new ArrayList<>();
		for(int i = 0; i<cartList.size();i++) {
			OrderValidator.validateStock(cartList.get(i), productList.get(i));			
			order.setAmount(order.getAmount()+(cartList.get(i).getQuantity()*productList.get(i).getPrice()));
			
			ProductOrderedEntity prodO = new ProductOrderedEntity();
			prodO.setSellerId(productList.get(i).getSellerId());
			prodO.setCompoundKey(new CompoundKey(cartList.get(i).getBuyerId(),productList.get(i).getProdId()));
			prodO.setQuantity(cartList.get(i).getQuantity());
			productsOrdered.add(prodO);				
		}		
		prodOrderedRepository.saveAll(productsOrdered);
		orderRepository.save(order);
		OrderPlacedDTO orderPlaced = new OrderPlacedDTO();
		orderPlaced.setBuyerId(order.getBuyerId());
		orderPlaced.setOrderId(order.getOrderId());
		Integer rewardPts = (int) (order.getAmount()/100);		
		orderPlaced.setRewardPoints(rewardPts);
		
		
		return orderPlaced;
	}

	@Override
	public List<OrderDTO> viewOrdersByBuyer(String buyerId) throws OrderException {
		List<OrderEntity> orders = orderRepository.findByBuyerId(buyerId);
		if(orders.isEmpty()) throw new OrderException("No orders available for given BuyerID");
		List<OrderDTO> dtoList = new ArrayList<>();
		orders.forEach(order->{
			OrderDTO odto = new OrderDTO();
			odto.setOrderId(order.getOrderId());
			odto.setBuyerId(order.getBuyerId());
			odto.setAmount(order.getAmount());
			odto.setAddress(order.getAddress());
			odto.setDate(order.getDate());
			odto.setStatus(order.getStatus());
			dtoList.add(odto);
		});
		return dtoList;
	}

	@Override
	public OrderDTO viewOrder(String orderId) throws OrderException {
		Optional<OrderEntity> optional = orderRepository.findByOrderId(orderId);
		OrderEntity order = optional.orElseThrow(()->new OrderException("Order does not exist"));
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setOrderId(order.getOrderId());
		orderDTO.setBuyerId(order.getBuyerId());
		orderDTO.setAmount(order.getAmount());
		orderDTO.setAddress(order.getAddress());
		orderDTO.setDate(order.getDate());
		orderDTO.setStatus(order.getStatus());		
		return orderDTO;
	}

	@Override
	public String reOrder(String buyerId, String orderId) throws OrderException {
		Optional<OrderEntity> optional = orderRepository.findByOrderId(orderId);
		OrderEntity order = optional.orElseThrow(()->new OrderException("Order does not exist for the given buyer"));
		OrderEntity reorder = new OrderEntity();
		String id = "O" + o++;
		reorder.setOrderId(id);
		reorder.setBuyerId(order.getBuyerId());
		reorder.setAmount(order.getAmount());
		reorder.setAddress(order.getAddress());
		reorder.setDate(LocalDate.now());
		reorder.setStatus(order.getStatus());
		
		orderRepository.save(reorder);		
		return reorder.getOrderId();
	}


}
