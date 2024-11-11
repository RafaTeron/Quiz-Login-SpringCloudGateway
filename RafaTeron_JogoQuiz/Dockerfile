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
COPY --from=build /app/target/JogoQuiz-0.0.1-SNAPSHOT.jar /app.jar

# Expõe a porta configurada
EXPOSE 8001

# Configura as variáveis de ambiente para o banco de dados PostgreSQL
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/springboot_jogoquiz \
    SPRING_DATASOURCE_USERNAME=postgres \
    SPRING_DATASOURCE_PASSWORD=5718293

# Comando para rodar o aplicativo, aguardando o Eureka na porta 8761
ENTRYPOINT ["java", "-jar", "/app.jar"]
