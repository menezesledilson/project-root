# Fase de Build
FROM ubuntu:latest AS build

# Atualiza os pacotes e instala o OpenJDK 17 e o Maven
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven

# Define o diretório de trabalho para o Maven
WORKDIR /app

# Copia o arquivo pom.xml e o diretório src para o diretório de trabalho
COPY pom.xml .
COPY src ./src

# Copia também o Maven Wrapper
COPY mvnw .
COPY .mvn .mvn

# Concede permissão de execução para o Maven Wrapper
RUN chmod +x mvnw

# Compila o projeto e gera o arquivo JAR
RUN ./mvnw clean install

# Fase de Execução
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho para o Java
WORKDIR /app

# Expõe a porta que a aplicação usará
EXPOSE 8080

# Copia o JAR da fase de build para a fase de execução
COPY --from=build /app/target/project-root-0.0.1-SNAPSHOT.jar app.jar

# Define o comando de execução da aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
