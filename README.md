# Tax Management System

### Description:
- There are 2 roles: User (Tax Payer) and Admin (Tax Inspector).
- User (Natural type or Legal type) must be authorised beforehand to submit their Tax Report to the Tax Office.
- User can edit Tax Reports
- Inspector may send a Report back to User for reconsideration if the Report is to be revised
- User may view all their Reports, sort them by Dates (Statement Period Date, Date of Report, Last Modified Date) 
and filter by Report statuses (pending, approved, regected).
- Report may be uploaded from the existing JSON file
- Inspector may approve or reject a Report indicating the reason of their decision in the Comment Section.
- Inspector reviews the submitted Reports from multiple Users
- Inspector filters the Reports by First or Last Name
- Inspector filters the Reports by Report type (Natural type or Legal type)

### Implementation:
- Front-End: Thymeleaf, Bootstrap, HTML, CSS, JavaScript
- Back-end: Java 8, SQL

### Tech:
- Spring Boot
- Spring Security
- MySQL
- Maven

### How To Install the App:
- Clone ```https://github.com/vadym-gonchar/tax-management-system.git```
- Install MySQL
- In your MySQLWorkbench create database ```tax_management```
- Insert the initial data to the tables using ```V01_02__final.sql```
ANY password is: ```123qwe```
- In ```application.properties``` use your OWN username and password
- Run the server and go to ```http://localhost:8080```

### View:
