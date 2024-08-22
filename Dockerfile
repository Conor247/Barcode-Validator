# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Container working directory
WORKDIR /src

COPY build/libs/barcode-validator-0.0.1-SNAPSHOT.jar /app/barcode-validator-0.0.1-SNAPSHOT.jar
COPY src/main/resources/weights.json src/main/resources/weights.json

EXPOSE 8084

# Run the JAR file
ENTRYPOINT ["java","-jar","/app/barcode-validator-0.0.1-SNAPSHOT.jar"]