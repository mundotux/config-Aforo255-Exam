#!/bin/bash
echo ============================================================
echo Generate JAR
echo ============================================================
mvn clean package -DskipTests
echo ============================================================
echo BUILD and PUSH Project to Docker
echo ============================================================
docker build -t config-server:v1 .
docker run -p 8888:8888 --name aforotest-config-server --network aforo255-test -d config-server:v1
echo ============================================================
echo End Process
echo ============================================================