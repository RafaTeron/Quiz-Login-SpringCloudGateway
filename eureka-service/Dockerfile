# Etapa de build
FROM eclipse-temurin:17-jdk-jammy AS build

WORKDIR /app

# Copia o Maven Wrapper e as configurações do projeto
COPY mvnw . 
COPY .mvn .mvn
COPY pom.xml . 
RUN ./mvnw dependency:resolve

# Copia o código-fonte do projeto e compila o aplicativo
COPY src src
RUN ./mvnw package -DskipTests

# Etapa final para rodar o aplicativo
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Define um usuário sem privilégios para executar a aplicação
RUN adduser --disabled-password --gecos "" appuser
USER appuser

# Copia o jar do aplicativo compilado na etapa de build
COPY --from=build /app/target/eureka-service-0.0.1-SNAPSHOT.jar /app.jar

# Expõe a porta do Eureka (8761)
EXPOSE 8761

# Comando para rodar o Eureka (sem necessidade de esperar por outros serviços)
ENTRYPOINT ["java", "-jar", "/app.jar"]
