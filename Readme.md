Command Line Based IT Issue Log Management System

Project Overview
The Command Line Based IT Issue Log Management Systemis a Java-based backend application that allows users to log and view IT-related issues using a command-line interface.  
The system uses a MySQL database to store issue details and follows a layered architecture to ensure clean separation of concerns.

This project is designed to demonstrate core backend concepts such as database connectivity, CRUD operations, and structured project design.

 Objectives
- To provide a simple command-line system for logging IT issues
- To store and retrieve issue data using a MySQL database
- To implement a clean layered architecture using Java
- To understand JDBC and database connectivity

Technologies Used
- Java
- MySQL
- JDBC (MySQL Connector/J)
- VS Code
- Git & GitHub



Project Structure
ANUDIP1/
│
├── src/
│ ├── db/
│ │ └── DBConnection.java
│ ├── dto/
│ │ └── Issue.java
│ ├── dao/
│ │ └── IssueDAO.java
│ ├── daoimpl/
│ │ └── IssueDAOImpl.java
│ ├── service/
│ │ └── IssueService.java
│ └── main/
│ └── MainApp.java
│
├── lib/
│ └── mysql-connector-j-8.0.33.jar
│
├── bin/
├── .vscode/
│ └── settings.json
└── README.md



 Database Details

 Database Name
it_issue_db


Table Structure
sql
CREATE TABLE issues (
    issue_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_name VARCHAR(50),
    department VARCHAR(50),
    issue_description VARCHAR(200),
    status VARCHAR(20)
);


Features
Log IT issues with employee details
View all logged IT issues
Command-line based interaction
Uses MySQL for persistent storage
Clean separation using DAO, DTO, and Service layers


 How to Compile and Run
Step 1: Compile (from src folder)
javac -d ..\bin db\DBConnection.java dto\Issue.java dao\IssueDAO.java daoimpl\IssueDAOImpl.java service\IssueService.java main\MainApp.java
Step 2: Run (from bin folder)
java -cp ".;..\lib\mysql-connector-j-8.0.33.jar" main.MainApp
Sample Output
1. Log IT Issue
2. View Issues
3. Exit
   
Enter choice:

 Architecture Explanation
DTO (Data Transfer Object): Represents issue data
DAO (Data Access Object): Defines database operations
DAO Implementation: Implements SQL logic
Service Layer: Handles business logic
Main Class: Provides CLI interface to users
DBConnection: Manages database connectivity

Advantages
Simple and easy to understand
Real-world IT support use case
Follows standard backend architecture
Suitable for academic and lab submission

 Future Enhancements
Update and close issues
Add priority levels
User authentication
Web or GUI interface

 Conclusion
This project successfully demonstrates a command-line based IT Issue Log Management System using Java and MySQL. It provides hands-on experience with JDBC, layered architecture, and backend development fundamentals.

 Author
Preetam Kage

