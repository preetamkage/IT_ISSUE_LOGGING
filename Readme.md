# 📌 IT Issue Logging System

A desktop-based **Java Swing + MySQL** application to log, manage, and track IT issues through a structured multi-screen GUI.

---

## 🚀 Features

* Add new IT issues
* View all issues in table format
* Update issue description
* Delete issues by ID
* Dashboard-based navigation
* MySQL integration using JDBC

---

## 🧱 Tech Stack

* Java (Swing)
* JDBC
* MySQL

---

## 📁 Project Structure

```
IT_ISSUE_LOGGING/
│
├── src/        → Contains all Java source files (.java)
│   ├── dao/
│   ├── daoimpl/
│   ├── db/
│   ├── dto/
│   ├── service/
│   ├── main/
│   └── ui/
│
├── out/        → Contains compiled files (.class)
│
├── lib/        → External libraries
│   └── mysql-connector-j-8.0.33.jar
│
├── setup.sql
├── run.bat
├── run.sh
└── README.md
```

---

## 🗄️ Database Setup (Run once)

### 1. Start MySQL

```
mysql -u root -p
```

### 2. Create Database & Table

```sql
CREATE DATABASE IF NOT EXISTS issue_logger;
USE issue_logger;

CREATE TABLE IF NOT EXISTS issues (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employee_name VARCHAR(100),
    department VARCHAR(100),
    issue_description TEXT,
    date_logged TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### 3. Exit

```
exit;
```

---

## ⚙️ Configuration

Update database credentials in:

```
src/db/DBConnection.java
 
```

Modify the following fields:

```
private static final String USER = "root";          // Your MySQL username
private static final String PASSWORD = "your_password"; // Your MySQL password
```

### 📌 Notes

* `issue_logger` → must match your database name
* `root` → replace if using a different MySQL user
* `your_password` → set your actual MySQL password

  

---

## ▶️ How to Run

### 1. Navigate to project

```
cd D:\IT_ISSUE_LOGGING
```

### 2. Clean old build

```
Get-ChildItem -Recurse -Filter "*.class" | Remove-Item -Force
Remove-Item -Recurse -Force out
New-Item -ItemType Directory -Name out
```

### 3. Compile project

```
javac -encoding UTF-8 -cp ".;lib/mysql-connector-j-8.0.33.jar" -d out src/dto/*.java src/dao/*.java src/db/*.java src/daoimpl/*.java src/service/*.java src/ui/*.java src/main/*.java
```

### 4. Run application

```
java -cp ".;out;lib/mysql-connector-j-8.0.33.jar" main.MainApp
```

---

## 🎯 Application Flow

1. Dashboard opens
2. Choose operation:

   * Add Issue
   * View Issues
   * Update Issue
   * Delete Issue
3. Perform action via separate screens

---

## 🧪 Verification

After adding data via GUI:

```
mysql -u root -p
USE issue_logger;
SELECT * FROM issues;
```

---

## ⚠️ Notes

* Ensure MySQL server is running
* Ensure correct DB credentials
* `src/` contains `.java` files
* `out/` contains compiled `.class` files
* Always recompile after code changes

---

## 🚀 Future Enhancements

* Search/filter issues
* Status tracking (OPEN/CLOSED)
* Authentication system
* Modern UI (sidebar layout)

---

## 👨‍💻 Author

Preetam Kage
CSE – 6th Sem

---
