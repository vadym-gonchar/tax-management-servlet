# Tax Management System

### Description:
- There are 2 roles: User (Tax Payer) and Admin (Tax Inspector).
- User must be authorised beforehand to submit their Tax Report to the Tax Office.
- User can edit Tax Reports
- Inspector may send a Report back to User for reconsideration if the Report is to be revised
- User may view all their Reports, sort them by Dates (Statement Period Date, Date of Report) 
and filter by Report statuses (pending, approved, regected).
- Inspector may approve or reject a Report indicating the reason of their decision in the Comment Section.
- Inspector reviews the submitted Reports from multiple Users
- Inspector filters the Reports by First or Last Name

### Implementation:
- Front-End: Bootstrap, HTML, CSS, JavaScript
- Back-end: Java 8, SQL

### Tech:
- Java EE (JSP, JDBC, Servlets)
- Apache Tomcat 8
- MySQL
- Maven

### How To Install the App:
- Clone ```https://github.com/vadym-gonchar/tax-management-servlet.git```
- Install MySQL
- In your MySQLWorkbench create database ```tax_management_servlet```
- Insert the initial data to the tables using ```V01_00__init.sql```
ANY password is: ```123qwe```
- In ```application.properties``` and ```context.xml``` use your OWN username and password
- Set up and configure Apache Tomcat 8.5.66
- Run the server to start with ```http://localhost:8080/login```

### View:
<img width="1791" alt="1" src="https://user-images.githubusercontent.com/61626216/120942365-226fa880-c731-11eb-83c7-d457b58bc78a.png">
<img width="1791" alt="2" src="https://user-images.githubusercontent.com/61626216/120942392-521eb080-c731-11eb-811d-11402acd1483.png">
<img width="1791" alt="3" src="https://user-images.githubusercontent.com/61626216/120942406-6662ad80-c731-11eb-9670-8e0bd891d2ed.png">
<img width="1791" alt="4" src="https://user-images.githubusercontent.com/61626216/120942420-7a0e1400-c731-11eb-9f9f-3926219b2218.png">
<img width="1791" alt="5" src="https://user-images.githubusercontent.com/61626216/120942430-88f4c680-c731-11eb-80d8-20e354379a5e.png">
<img width="1789" alt="6" src="https://user-images.githubusercontent.com/61626216/120942442-9c079680-c731-11eb-9d42-16c12eb81ea4.png">
<img width="1789" alt="7" src="https://user-images.githubusercontent.com/61626216/120942447-af1a6680-c731-11eb-979b-8f05b636ed71.png">
<img width="1791" alt="8" src="https://user-images.githubusercontent.com/61626216/120942448-b04b9380-c731-11eb-80a9-d35fbef26290.png">
<img width="1788" alt="9" src="https://user-images.githubusercontent.com/61626216/120942449-b0e42a00-c731-11eb-8f9c-4843b0a521ea.png">
<img width="1791" alt="10" src="https://user-images.githubusercontent.com/61626216/120942450-b0e42a00-c731-11eb-8564-1153f8d87457.png">











