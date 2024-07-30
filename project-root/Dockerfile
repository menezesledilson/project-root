# Use a imagem base do OpenJDK 17 para a construção
FROM openjdk:17-jdk-slim AS build

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo POM e o diretório src para o diretório de trabalho
COPY pom.xml ./
COPY src ./src

# Instala Maven
RUN apt-get update && apt-get install -y maven

# Executa o Maven para compilar a aplicação
RUN mvn clean package -DskipTests

# Use uma imagem base do OpenJDK 17 para o runtime
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o JAR da aplicação do estágio de build para o diretório de trabalho
COPY --from=build /app/target/project-root-0.0.1-SNAPSHOT.jar /app/project-root-0.0.1-SNAPSHOT.jar

# Exponha a porta na qual sua aplicação está rodando
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "/app/project-root-0.0.1-SNAPSHOT.jar"]
