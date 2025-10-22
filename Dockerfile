# Usa uma imagem base com Java 21
FROM eclipse-temurin:21-jdk

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o JAR gerado para dentro do contêiner
COPY target/personal-finance-tracker-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta 8080
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]