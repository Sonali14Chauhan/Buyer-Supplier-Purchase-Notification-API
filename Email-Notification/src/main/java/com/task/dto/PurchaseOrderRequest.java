package com.task.dto;

import java.util.List;

import com.task.model.Buyer;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseOrderRequest {

	private Long buyerId;
	private Long supplierId;
	private List<ItemRequest> items;
}
