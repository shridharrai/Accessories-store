package com.shridhar.ecommerce.service;

import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shridhar.ecommerce.dao.CustomerRepository;
import com.shridhar.ecommerce.dto.Purchase;
import com.shridhar.ecommerce.dto.PurchaseResponse;
import com.shridhar.ecommerce.entity.Customer;
import com.shridhar.ecommerce.entity.Order;
import com.shridhar.ecommerce.entity.OrderItem;

@Service
public class CheckoutServiceImpl implements CheckoutService {
	
	private CustomerRepository customerRepository;
	
	@Autowired
	public CheckoutServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	@Override
	@Transactional
	public PurchaseResponse placeOrder(Purchase purchase) {
		// retrieve the order info from dto
		Order order = purchase.getOrder();
		
		// generate tracking number
		String orderTrackingNumber = generateOrderTrackingNumber();
		order.setOrderTrackingNumber(orderTrackingNumber);
		
		// populate order with orderItems
		Set<OrderItem> orderItems = purchase.getOrderItems();
		orderItems.forEach(item -> order.add(item));
		
		// populate order with shipping and billing address
		order.setBillingAddress(purchase.getBillingAddress());
		order.setShippingAddress(purchase.getShippingAddress());
		
		// populate customer with order
		Customer customer = purchase.getCustomer();
		customer.add(order);
		
		// save to the database
		customerRepository.save(customer);
	
		// return response
		return new PurchaseResponse(orderTrackingNumber);
	}

	private String generateOrderTrackingNumber() {
		// generate a random UUID number(UUID version-4)
		return UUID.randomUUID().toString();
	}

}
