# Build stage
FROM maven:3.8.1-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests -Djar.finalName=bc-api-dev.jar

# Run stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar bc-api-dev.jar
EXPOSE 8080
CMD ["java", "-jar", "-Dspring.profiles.active=dev", "/app/bc-api-dev.jar"]
