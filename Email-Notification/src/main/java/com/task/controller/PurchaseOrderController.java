package com.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.dto.PurchaseOrderRequest;
import com.task.model.PurchaseOrder;
import com.task.service.PurchaseOrderService;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseOrderController {

	@Autowired
	private PurchaseOrderService orderService;
	
	@PostMapping
	public PurchaseOrder createPurchaseOrder(@RequestBody PurchaseOrderRequest orderRequest) {
		return orderService.createPurchaseOrder(orderRequest);
		
	}
	
}
