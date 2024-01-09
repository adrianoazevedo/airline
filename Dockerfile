# Build stage JDK
FROM maven:3.9-eclipse-temurin-17-alpine as build

WORKDIR /app
COPY . .
RUN mvn clean package -X -DskipTests


# Build stage build
FROM openjdk:17-ea-10-jdk-slim

EXPOSE 8080
WORKDIR /app
COPY --from=build  ./app/target/*.jar ./app.jar
ENTRYPOINT [ "java", "-jar", "app.jar" ]