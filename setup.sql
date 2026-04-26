-- ============================================================
--  IT Issue Logging System — MySQL Setup Script
--  Run this ONCE in MySQL before launching the application.
-- ============================================================

-- 1. Create the database
CREATE DATABASE IF NOT EXISTS issue_logger
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE issue_logger;

-- 2. Create the issues table
CREATE TABLE IF NOT EXISTS issues (
    id                INT           AUTO_INCREMENT PRIMARY KEY,
    employee_name     VARCHAR(100)  NOT NULL,
    department        VARCHAR(100)  NOT NULL,
    issue_description TEXT          NOT NULL,
    date_logged       TIMESTAMP     DEFAULT CURRENT_TIMESTAMP
);

-- 3. (Optional) Insert sample data to verify the setup
INSERT INTO issues (employee_name, department, issue_description) VALUES
    ('Arjun Sharma',   'Finance',  'Cannot access accounting software after password reset.'),
    ('Priya Nair',     'HR',       'Laptop freezes when opening MS Teams during calls.'),
    ('Ravi Kumar',     'IT',       'VPN disconnects every 30 minutes on Windows 11.');

-- 4. Verify
SELECT * FROM issues;