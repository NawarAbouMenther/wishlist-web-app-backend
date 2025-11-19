# ---- Build-Stage: Gradle baut das Spring-Boot-JAR ----
FROM gradle:8.4.0-jdk17 AS build
WORKDIR /app

# komplettes Projekt kopieren (build.gradle, src/, etc.)
COPY . .

# Spring-Boot-JAR bauen
RUN gradle build --no-daemon

# ---- Run-Stage: nur das fertige JAR ausführen ----
FROM eclipse-temurin:17-jre
WORKDIR /app

# das erzeugte JAR aus dem Build-Container kopieren
COPY --from=build /app/build/libs/*.jar app.jar

# Spring Boot läuft standardmäßig auf 8080
EXPOSE 8080

# Startbefehl
CMD ["java", "-jar", "app.jar"]
