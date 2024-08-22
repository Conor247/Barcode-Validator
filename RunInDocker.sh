#!/bin/bash

# Run Gradle clean and build
./gradlew clean build

# If the build was successful, start Docker Compose
if [ $? -eq 0 ]; then
    docker-compose up
else
    echo "Gradle build failed. Docker Compose will not be started."
fi