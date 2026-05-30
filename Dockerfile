# syntax=docker/dockerfile:1

# ---------- build stage ----------
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Cache de dependencias: copio sólo el wrapper + pom para que esta capa
# se reuse mientras pom.xml no cambie.
COPY .mvn .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw && ./mvnw -B dependency:go-offline

# Código fuente y build del jar.
COPY src src
RUN ./mvnw -B package -DskipTests

# ---------- runtime stage ----------
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
