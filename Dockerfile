FROM maven:3.8.3-jdk-8 AS build

WORKDIR /out
COPY . .
RUN mvn package
FROM openjdk:8-jre-slim
WORKDIR /out
EXPOSE 8080
COPY --from=build /artifacts/api_jar/api.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]