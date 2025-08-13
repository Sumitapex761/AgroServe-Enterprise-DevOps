# Step 1: Use an official OpenJDK runtime as base image
FROM openjdk:17-jdk-slim

# Step 2: Set a working directory inside the container
WORKDIR /app

# Step 3: Copy the JAR file from your target folder into the container
# Replace 'app.jar' with your actual jar name
COPY target/*.jar app.jar

# Step 4: Expose application port (default for Spring Boot is 8080)
EXPOSE 8080

# Step 5: Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
