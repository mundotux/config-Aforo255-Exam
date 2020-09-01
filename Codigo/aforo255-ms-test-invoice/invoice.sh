#!/bin/bash
echo ============================================================
echo Generate JAR
echo ============================================================
mvn clean package -DskipTests
echo ============================================================
echo BUILD and PUSH Project to Docker
echo ============================================================
docker build -t app-invoices:v1 .
docker run -p 8081:8081 --name aforotest-app-invoices --network aforo255-test -d app-invoices:v1
echo ============================================================
echo End Process
echo ============================================================