package com.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.model.Product;
import com.task.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repo;

	public Product addProduct(Product product) {
		return repo.save(product);
		
	}

}
