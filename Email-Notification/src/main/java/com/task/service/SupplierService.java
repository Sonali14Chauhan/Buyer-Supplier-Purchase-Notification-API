package com.task.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.model.Supplier;
import com.task.repository.SupplierRepository;


@Service
public class SupplierService {

	@Autowired
	private SupplierRepository repo;
	
	@Autowired
	private EmailService emailService;
	
	public Supplier createSupplier(Supplier supplier) {
		Supplier saved = repo.save(supplier);
		emailService.sendWelcomeMail(saved.getEmail(), "Welcome " + saved.getName(),
				"Hello " + saved.getName() + ",\\n\\nThank you for registration");
		return saved;
	}

}
