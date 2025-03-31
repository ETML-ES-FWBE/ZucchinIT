FROM maven:3.9.9-eclipse-temurin-17-alpine AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
RUN apk add --no-cache curl

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]