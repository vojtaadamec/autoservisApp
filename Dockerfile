# Sestavení aplikace
FROM maven:3.9-eclipse-temurin-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Spuštění aplikace
FROM eclipse-temurin:17-jdk
COPY --from=build /target/autoservis-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dserver.port=${PORT:8080}", "-jar", "app.jar"]