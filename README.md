# StockSurge Backend

MVP backend for StockSurge — an intelligent platform that helps businesses find value in deadstock and connects unused inventory with buyer requirements.

## Tech stack
- Java 17
- Spring Boot 3.5.5
- Spring Web
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok

## 1. Create PostgreSQL database
Create a database named:

stocksurge

## 2. Configure password
Open:
src/main/resources/application.properties

Change:
spring.datasource.password=CHANGE_ME

to your PostgreSQL password.

## 3. Run
Open the project in IntelliJ IDEA and run:

StockSurgeApplication.java

Server:
http://localhost:8080

## 4. Test inventory API

POST /api/inventory

Example JSON:
{
  "productName": "Cotton T-Shirts",
  "category": "Textile",
  "description": "100 unused cotton T-shirts from previous season",
  "quantity": 100,
  "conditionStatus": "GOOD",
  "pricePerUnit": 180,
  "location": "Bhopal",
  "imageUrl": "",
  "status": "ACTIVE"
}

Then:
GET /api/inventory

## Buyer requirement

POST /api/requirements

{
  "buyerName": "ABC Reseller",
  "category": "Textile",
  "description": "Need cotton t-shirts",
  "quantityRequired": 80,
  "minBudgetPerUnit": 100,
  "maxBudgetPerUnit": 200,
  "location": "Bhopal"
}

Generate matches:
POST /api/requirements/{id}/matches

View matches:
GET /api/requirements/{id}/matches

## Current MVP
The matching engine uses simple rules for category, quantity, budget, location and condition.

AI/Gemini, authentication, image upload, chat, payments and production security are intentionally not included yet.
