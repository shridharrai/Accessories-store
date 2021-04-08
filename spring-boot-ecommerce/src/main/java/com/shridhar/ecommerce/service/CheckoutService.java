package com.shridhar.ecommerce.service;

import com.shridhar.ecommerce.dto.Purchase;
import com.shridhar.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {
	PurchaseResponse placeOrder(Purchase purchase);
}
