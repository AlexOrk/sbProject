# Bank API
---
Title: Bank API  
Authors: Vladislav Bounegru, Alexey Orkhoyan  
Date: 09.10.2020
---
### Bank API project for SB
Web service implements the work of clients with bank accounts.

Available functionality
---
1. View balance
2. Deposit
3. View all cards
4. Order a new card

Interaction types
---
There are two types of controllers for interacting with the service.
1. Used to demonstrate working with JSON files.
2. Used to demonstrate working with the web GUI.
To work with type 2 controller, your path must start with "/api/"

Using
---
By clicking the link "/api/welcome" you can see the start page.
You can choose any of the 4 offered options.
1. By clicking on the link "View balance", you can see the balance on your accounts.
2. By clicking on the link "Deposit", you can select an account to deposit.
You need to select an account and click the "Select" button. Then enter the amount in the available window
and click the "Deposit" button. In a new window, you will see a list of your accounts with balance.
3. By clicking on the link "View all cards", you can see a list of all your cards.
4. By clicking on the link "Order a new card", you can select an account to order a new card.
You need to select an account and click the "Select" button. Then in a new window, you will see a list of
your cards.
You can always use the "Back" button to return to the previous step, or the "Back" button to return to the start page.


Technologies used in the project
---
- Application architecture and networking - `Spring Boot`
- View: `HTML + Thymeleaf, CSS`
- Working with entities using `Hibernate`.
- Authorization and data protection - `Spring Security`.
- For frontend development - `Bootstrap`.
- Logging is being did using `Logback`.
- Testing - `JUnit 4, Spring Boot Starter Test, Mockito`
- DBMS - `H2`
- Dependency manager - `Maven`
---
Note
---

The connection to the database is done in the `application.properties` file.


