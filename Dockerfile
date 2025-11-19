# ---- Build stage: Gradle + JDK 25 ----
FROM gradle:8.4.0-jdk25 AS build
WORKDIR /app

COPY . .

RUN gradle build --no-daemon

# ---- Run stage: JRE 25 ----
FROM eclipse-temurin:25-jre
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
