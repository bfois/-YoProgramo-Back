# Stage 1: Build the application using Maven
FROM maven:3.8.3-jdk-8 AS build
WORKDIR /src
COPY . .
RUN mvn package

# Stage 2: Create the final container with the JRE and the built JAR
FROM openjdk:8-jre-slim
WORKDIR /app
EXPOSE 8080

# Create the /app directory and copy the JAR from the Maven build stage
RUN mkdir /app
COPY --from=build /src/target/api.jar /app/app.jar

# Set the entrypoint to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]