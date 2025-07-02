# Build da Aplicação com MavenGG
FROM maven:3.9-eclipse-temurin-21 AS build
# Imagem base para este estágio

# Define o diretório de trabalho padrão dentro do contêiner para /app
WORKDIR /app

# Copia o repositório do GitHub
COPY . .

# Executa o comando Maven para limpar, compilar e empacotar a aplicação.
RUN mvn clean package -DskipTests

# Ambiente de Execução com WildFly
FROM quay.io/wildfly/wildfly:36.0.1.Final-jdk21

# Copia o arquivo .war gerado no estágio anterior
COPY --from=build /app/target/BibliotecaVirtual.war /opt/jboss/wildfly/standalone/deployments/