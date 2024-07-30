# Etapa 1: Construir o JAR
FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app
COPY . /app
RUN mvn clean package

# Etapa 2: Criar a imagem final
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/project-root-0.0.1-SNAPSHOT.jar /app/project-root-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/project-root-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
