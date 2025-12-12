package com.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.model.Buyer;
import com.task.service.BuyerService;

@RestController
@RequestMapping("/api/buyer")
public class BuyerController {

	@Autowired
	private BuyerService buyerService;
	
	@PostMapping
	private Buyer createBuyer(@RequestBody Buyer buyer) {
		return buyerService.createBuyer(buyer);
	}
}
