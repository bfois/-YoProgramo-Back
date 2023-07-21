# Stage 1: Build the application using Maven
FROM maven:3.8.3-jdk-8 AS build
WORKDIR /src
COPY . .
RUN mvn package -DskipTests

# Stage 2: Create the final container with the JRE and the built JAR
FROM openjdk:8-jre-slim
WORKDIR /app
EXPOSE 8080

COPY --from=build /out/artifacts/api_jar/api.jar /app/app.jar

# Set the entrypoint to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]