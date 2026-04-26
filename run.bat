@echo off
REM ============================================================
REM  IT Issue Logging System — Compile & Run (Windows)
REM  Run this script from the project root: D:\IT_ISSUE_LOGGING
REM ============================================================

SET SRC=src
SET OUT=out
SET LIB=lib\mysql-connector-j-8.0.33.jar
SET MAIN=main.MainApp

echo [1/3] Creating output directory...
if not exist %OUT% mkdir %OUT%

echo [2/3] Compiling all Java sources...
javac -cp ".;%LIB%" -d %OUT% ^
    %SRC%\dto\Issue.java ^
    %SRC%\dao\IssueDAO.java ^
    %SRC%\db\DBConnection.java ^
    %SRC%\daoimpl\IssueDAOImpl.java ^
    %SRC%\service\IssueService.java ^
    %SRC%\ui\IssueLoggerApp.java ^
    %SRC%\main\MainApp.java

IF %ERRORLEVEL% NEQ 0 (
    echo.
    echo [ERROR] Compilation failed. Fix errors above and retry.
    pause
    exit /b 1
)

echo [3/3] Launching GUI application...
java -cp ".;%OUT%;%LIB%" %MAIN%

pause