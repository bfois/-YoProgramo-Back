FROM maven:3.8.3-jdk-8 AS build

WORKDIR /src
COPY . .
RUN mvn package
FROM openjdk:8-jre-slim
WORKDIR /src
EXPOSE 8080
COPY --from=build /src/target/YOPROGRAMO-BACK-1.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]