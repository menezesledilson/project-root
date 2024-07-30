# Use a imagem base do OpenJDK 17
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o JAR da aplicação para o diretório de trabalho
COPY target/project-root-0.0.1-SNAPSHOT.jar /app/project-root-0.0.1-SNAPSHOT.jar

# Exponha a porta na qual sua aplicação está rodando
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "/app/project-root-0.0.1-SNAPSHOT.jar"]
