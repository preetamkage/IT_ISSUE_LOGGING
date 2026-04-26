#!/bin/bash
# ============================================================
#  IT Issue Logging System — Compile & Run (Linux / macOS)
#  Run from project root: ./run.sh
# ============================================================

SRC="src"
OUT="out"
LIB="lib/mysql-connector-j-8.0.33.jar"
MAIN="main.MainApp"

echo "[1/3] Creating output directory..."
mkdir -p "$OUT"

echo "[2/3] Compiling all Java sources..."
javac -cp ".:$LIB" -d "$OUT" \
    "$SRC/dto/Issue.java" \
    "$SRC/dao/IssueDAO.java" \
    "$SRC/db/DBConnection.java" \
    "$SRC/daoimpl/IssueDAOImpl.java" \
    "$SRC/service/IssueService.java" \
    "$SRC/ui/IssueLoggerApp.java" \
    "$SRC/main/MainApp.java"

if [ $? -ne 0 ]; then
    echo "[ERROR] Compilation failed."
    exit 1
fi

echo "[3/3] Launching GUI application..."
java -cp ".:$OUT:$LIB" "$MAIN"