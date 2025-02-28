# Use a valid Maven image with the desired JDK version as the build stage
FROM maven:3.8.8-eclipse-temurin-21 AS build

# Define the working directory
WORKDIR /app

# Copy the Maven project files into the container
COPY pom.xml .
COPY src ./src

# Build the Maven project to generate the JAR file
RUN mvn clean package

# Use a base OpenJDK image for the runtime stage
FROM openjdk:21-jdk-slim

# Define the working directory
WORKDIR /app

# Copy the generated JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port on which the application runs
EXPOSE 3000

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]