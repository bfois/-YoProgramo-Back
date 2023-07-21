FROM maven:3.8.3-jdk-8 AS build

WORKDIR /app
COPY . .
RUN mvn package
FROM openjdk:8-jre-slim
WORKDIR /app
EXPOSE 8080
COPY --from=build /app/target/YOPROGRAMO-BACK-1.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]