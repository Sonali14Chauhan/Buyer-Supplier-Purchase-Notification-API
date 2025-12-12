package com.task.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.dto.ItemRequest;
import com.task.dto.PurchaseOrderRequest;
import com.task.model.Buyer;
import com.task.model.Product;
import com.task.model.PurchaseOrder;
import com.task.model.PurchaseOrderItem;
import com.task.model.Supplier;
import com.task.repository.BuyerRepository;
import com.task.repository.ProductRepository;
import com.task.repository.PurchaseOrderRepository;
import com.task.repository.SupplierRepository;

@Service
public class PurchaseOrderService {
	
	@Autowired
	private BuyerRepository buyerRepository;
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private PurchaseOrderRepository orderRepository;
	
	@Autowired
	private EmailService emailService;
	

	public PurchaseOrder createPurchaseOrder(PurchaseOrderRequest orderRequest) throws RuntimeException {
		
		Buyer buyer = buyerRepository.findById(orderRequest.getBuyerId())
				.orElseThrow(() -> new RuntimeException("Buyer not found"));
		
		Supplier supplier = supplierRepository.findById(orderRequest.getSupplierId())
				.orElseThrow(() -> new RuntimeException("Supplier not found"));
	
		List<PurchaseOrderItem> items = new ArrayList<PurchaseOrderItem>();
		int total = 0;
		
		for(ItemRequest item : orderRequest.getItems()) {
			Product p = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + item.getProductId()));
			
			int line = item.getQuantity() * p.getUnitPrice();
			PurchaseOrderItem poi = PurchaseOrderItem.builder()
							.productId(p.getId())
							.quantity(item.getQuantity())
							.unitPrice(p.getUnitPrice())
							.lineTotal(line)
							.build();
			items.add(poi);
			total += line;	
		}
		
		PurchaseOrder po = PurchaseOrder.builder()
						.buyerId(buyer.getId())
						.supplierId(supplier.getId())
						.items(items)
						.totalAmount(total)
						.createdAt(LocalDateTime.now())
						.status("CREATED")
						.build();
	
		PurchaseOrder saved = orderRepository.save(po);
		
		emailService.sendPurchaseOrderMail(saved , buyer, supplier);
	
		return saved;
	}

}
