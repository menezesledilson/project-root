# Estágio de construção
FROM ubuntu:latest AS build

# Atualiza e instala dependências
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven

# Define o diretório de trabalho
WORKDIR /app

# Copia o código fonte para o container
COPY . .

# Compila o projeto
RUN mvn clean install

# Estágio final
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho
WORKDIR /app

# Expõe a porta 8080
EXPOSE 8080

# Copia o arquivo JAR do estágio de construção
COPY --from=build /app/target/project-root-0.0.1-SNAPSHOT.jar app.jar

# Define o ponto de entrada
ENTRYPOINT ["java", "-jar", "app.jar"]
