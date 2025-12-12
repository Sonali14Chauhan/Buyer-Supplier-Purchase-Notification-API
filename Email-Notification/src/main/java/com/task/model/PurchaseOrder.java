package com.task.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long buyerId;
	private Long supplierId;
	private int totalAmount;
	private String status;
	private LocalDateTime createdAt;
	
	
	@OneToMany(mappedBy = "purchaseOrder" ,cascade = CascadeType.ALL, orphanRemoval = true)
	List<PurchaseOrderItem> items;

}
