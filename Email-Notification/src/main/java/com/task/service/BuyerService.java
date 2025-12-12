package com.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.model.Buyer;
import com.task.repository.BuyerRepository;

@Service
public class BuyerService {
	
	@Autowired
	private BuyerRepository buyerRepository;
	
	@Autowired
	private EmailService emailService;

	public Buyer createBuyer(Buyer buyer) {
		Buyer saved =  buyerRepository.save(buyer);
		emailService.sendWelcomeMail(saved.getEmail(), 
									"Welcome "+saved.getName() ,
									"Hello " +saved.getName()+ ",\\n\\nThank you for registration" );
		return saved;
	}

}
