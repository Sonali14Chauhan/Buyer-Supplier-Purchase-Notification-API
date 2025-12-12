package com.task.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.task.model.Buyer;
import com.task.model.PurchaseOrder;
import com.task.model.PurchaseOrderItem;
import com.task.model.Supplier;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
//	public void sendWelcomeMailToBuyer(Buyer buyer) {
//		SimpleMailMessage msg = new SimpleMailMessage();
//		msg.setTo(buyer.getEmail());
//		msg.setSubject("Welcome " + buyer.getName());
//		msg.setText("Hello " + buyer.getName() + "Has Successfully register");
//	    javaMailSender.send(msg);
//	}
//	
//	public void sendWelcomeMailToSupplier(Supplier supplier) {
//		SimpleMailMessage msg = new SimpleMailMessage();
//		msg.setTo(supplier.getEmail());
//		msg.setSubject("Welcome " + supplier.getName());
//		msg.setText("Hello " + supplier.getName() + "Has Successfully register");
//	    javaMailSender.send(msg);
//	}
	
	
	public void sendWelcomeMail(String to, String subject, String text) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(to);
		
		
		msg.setSubject(subject);
		msg.setText(text);
	    mailSender.send(msg);
	}

//	public void sendPurchaseOrderMail(PurchaseOrder po, Buyer buyer, Supplier supplier) {
//		SimpleMailMessage msg = new SimpleMailMessage();
//        msg.setTo(supplier.getEmail());
//        msg.setSubject("New Purchase Order #" + po.getId() + " from " + buyer.getName());
//
//        String itemsText = po.getItems().stream()
//                .map(i ->String.format("%s — Qty: %d — Unit: %d — Line: %d",
//                        i.getProductId(),
//                        i.getQuantity(),
//                        i.getUnitPrice(),
//                        i.getLineTotal()
//                ))
//                .collect(Collectors.joining("\n"));
//
//        String body = new StringBuilder()
//                .append("Purchase Order #: ").append(po.getId()).append("\n")
//                .append("Order Date: ").append(po.getCreatedAt()).append("\n\n")
//                .append("Buyer: ").append(buyer.getName()).append("\n")
//                .append("Buyer Email: ").append(buyer.getEmail()).append("\n")
//                .append("Buyer Address: ").append(buyer.getAddress() == null ? "-" : buyer.getAddress()).append("\n\n")
//                .append("Items:\n").append(itemsText).append("\n\n")
//                .append("Total Amount: ").append(String.format("%d", po.getTotalAmount())).append("\n")
//                .append("Please confirm receipt.\n\nRegards,\n").append(buyer.getName())
//                .toString();
//
//        msg.setText(body);
//        mailSender.send(msg);
//
//	}
	
	public void sendPurchaseOrderMail(PurchaseOrder po, Buyer buyer, Supplier supplier) {
		
		MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
		
        
        helper.setTo(supplier.getEmail());
        helper.setSubject("New Purchase Order #" + po.getId() + " from " + buyer.getName());

        // ---------- Banner Image  ----------
        String bannerUrl = "https://imgs.search.brave.com/bzLbsGIuELGYX5Dgz14URwQpLUQl7VTZCxjDMYWHMUs/rs:fit:0:180:1:0/g:ce/aHR0cHM6Ly9jZG4t/ZnJvbnQuZnJlZXBp/ay5jb20vaW1hZ2Vz/L2hvbWUvaW1hZ2Vz/LWRhcmsud2VicD93/PTE0NDAmaD0zMjA";  

        // ---------- Create Items Table ----------
        StringBuilder table = new StringBuilder();
        table.append("<table border='1' cellspacing='0' cellpadding='6' style='border-collapse: collapse; width: 100%;'>")
                .append("<tr style='background:#f2f2f2;'>")
                .append("<th>Product</th><th>Qty</th><th>Unit Price</th><th>Line Total</th>")
                .append("</tr>");

        for (PurchaseOrderItem i : po.getItems()) {
            table.append("<tr>")
                 .append("<td>").append(i.getProductId()).append("</td>")
                 .append("<td>").append(i.getQuantity()).append("</td>")
                 .append("<td>").append(i.getUnitPrice()).append("</td>")
                 .append("<td>").append(i.getLineTotal()).append("</td>")
                 .append("</tr>");
        }

        table.append("</table>");

        // ---------- Build Final HTML ----------
        String html = """
                <html>
                <body style="font-family: Arial, sans-serif;">

                <!-- Banner -->
                <img src='%s' alt='Purchase Order Banner' style='width:100%%; max-height:180px; object-fit:cover;'>

                <h2>Purchase Order #%d</h2>
                <p><strong>Order Date:</strong> %s</p>

                <h3>Buyer Details</h3>
                <p>
                <strong>Name:</strong> %s <br>
                <strong>Email:</strong> %s <br>
                <strong>Address:</strong> %s
                </p>

                <h3>Order Items</h3>
                %s

                <br><br>
                <h3>Total Amount: %d</h3>

                <p>Please confirm receipt.</p>

                <p>Regards,<br>%s</p>

                </body>
                </html>
                """.formatted(
                        bannerUrl,
                        po.getId(),
                        po.getCreatedAt(),
                        buyer.getName(),
                        buyer.getEmail(),
                        buyer.getAddress(),
                        table,
                        po.getTotalAmount(),
                        buyer.getName()
                );

        helper.setText(html, true); // true = HTML email

        mailSender.send(message);
        
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}

    } 


	
	
}
