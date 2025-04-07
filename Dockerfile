FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/*.jar app.jar


RUN mkdir -p /opt/datadog && \
    wget -O /opt/datadog/dd-java-agent.jar https://dtdg.co/latest-java-tracer

ENV DD_SERVICE=prevdent-java-mvc
ENV DD_ENV=dev
ENV DD_VERSION=1.0.0
ENV DD_LOGS_INJECTION=true
ENV DD_TRACE_ENABLED=true

EXPOSE 8081

CMD ["java", "-javaagent:/opt/datadog/dd-java-agent.jar", "-jar", "app.jar"]
