# Buyer-Supplier-Purchase-Notification-API

Spring Boot | Java | REST APIs | Email Automation

A backend application designed to automate the purchase order workflow between buyers and suppliers. The system allows buyers to create purchase orders, stores all data securely in a database, and automatically sends email notifications to suppliers with detailed PO information.

📌 Features

Buyer, Supplier, Product & Purchase Order Management CRUD APIs for all core procurement entities.
Automated Email Notifications Sends suppliers a formatted email when a new Purchase Order is created, including:
Buyer details
Itemized product list
Line-item totals
Grand total
Banner image
Modular Spring Boot Architecture Follows Controller → Service → Repository pattern for clean separation of concerns.

Database Persistence Uses JPA/Hibernate to reliably store and retrieve all procurement-related data.

🛠️ Tech Stack
Backend: Spring Boot, Java
Database: PostgreSQL
ORM: Spring Data JPA
Email Service: Spring Boot Mail Sender
Build Tool: Maven
