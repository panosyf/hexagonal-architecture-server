#!/bin/bash

set -e

echo "Building all modules..."
#mvn clean install -DskipTests
#./mvnw clean install -DskipTests
./mvnw clean install
echo ""
echo "Starting application..."
cd monolith-boot
#mvn spring-boot:run -Dspring-boot.run.profiles=local
.././mvnw spring-boot:run -Dspring-boot.run.profiles=local