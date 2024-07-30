# Fase de Build
FROM ubuntu:latest AS build

# Atualiza os pacotes e instala o OpenJDK 17
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven

# Define o diretório de trabalho
WORKDIR /app

# Copia o conteúdo do projeto para a imagem
COPY . .

# Compila o projeto e gera o arquivo JAR
RUN mvn clean install

# Fase de Execução
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho
WORKDIR /app

# Expõe a porta que a aplicação usará
EXPOSE 8080

# Copia o JAR da fase de build para a fase de execução
COPY --from=build /app/target/project-root-0.0.1-SNAPSHOT.jar app.jar

# Define o comando de execução da aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
