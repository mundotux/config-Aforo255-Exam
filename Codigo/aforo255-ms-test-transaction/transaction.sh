#!/bin/bash
echo ============================================================
echo Generate JAR
echo ============================================================
mvn clean package -DskipTests
echo ============================================================
echo BUILD and PUSH Project to Docker
echo ============================================================
docker build -t app-transaction:1.0 .
docker run -p 8082:8082 --name aforotest-app-transaction --network aforo255-test app-transaction:1.0
echo ============================================================
echo End Process
echo ============================================================