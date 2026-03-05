#!/bin/bash

set -e

# Default profile
PROFILE=${1:-local}

echo "Building all modules..."
#mvn clean install -DskipTests
#./mvnw clean install -DskipTests
./mvnw clean install
echo ""
echo "Starting application with profile: $PROFILE ..."
# If profile is local-postgres, start Docker Compose
if [ "$PROFILE" == "local-postgres" ]; then
    echo "Starting Postgres container via Docker Compose..."
    docker compose up -d
fi
cd monolith-boot
#mvn spring-boot:run -Dspring-boot.run.profiles="$PROFILE"
.././mvnw spring-boot:run -Dspring-boot.run.profiles="$PROFILE"