# Stage 1: Build the application using Maven
FROM maven:3.8.3-jdk-8 AS build
WORKDIR /app
COPY . .
RUN mvn package -DskipTests

# Stage 2: Create the final container with the JRE and the built JAR
FROM openjdk:8-jre-slim
WORKDIR /app
EXPOSE 8080

# Copiar el JAR desde la etapa de compilación (build stage)
COPY --from=build /app/out/artifacts/api_jar/api.jar /app/app.jar

# Establecer el punto de entrada para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]