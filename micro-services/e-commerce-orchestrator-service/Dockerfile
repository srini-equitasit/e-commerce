#FROM openjdk:8-jdk-alpine
FROM openjdk:11-slim
VOLUME /tmp
COPY target/*.jar e-commerce-orchestrator-service.jar
ENTRYPOINT ["java","-jar","/e-commerce-orchestrator-service.jar"]

EXPOSE 8081