package com.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
