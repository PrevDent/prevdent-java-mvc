# Etapa de build com Maven
FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

# Etapa de execução com JDK
FROM openjdk:17-slim

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

RUN apt-get update && apt-get install -y curl && \
    mkdir -p /opt/datadog && \
    curl -o /opt/datadog/dd-java-agent.jar -L https://dtdg.co/latest-java-tracer && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

HEALTHCHECK --interval=5s --timeout=3s --retries=10 \
  CMD wget -q -O /dev/null http://localhost:8081/actuator/health || exit 1



EXPOSE 8081

CMD ["java", "-javaagent:/opt/datadog/dd-java-agent.jar", "-Ddd.logs.injection=true", "-jar", "app.jar"]
