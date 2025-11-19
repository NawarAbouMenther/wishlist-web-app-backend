# ---- Build stage ----
FROM eclipse-temurin:25 AS build
WORKDIR /app

# Gradle Wrapper und Projekt kopieren
COPY . .

# Berechtigung für Gradle Wrapper (Linux benötigt das)
RUN chmod +x gradlew

# JAR bauen
RUN ./gradlew bootJar --no-daemon

# ---- Run stage ----
FROM eclipse-temurin:25
WORKDIR /app

COPY --from=build /app/build/libs/*SNAPSHOT.jar app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
