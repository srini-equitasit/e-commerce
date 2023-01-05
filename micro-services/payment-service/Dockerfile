#FROM openjdk:8-jdk-alpine
FROM openjdk:11-slim
VOLUME /tmp
COPY target/*.jar payment-service.jar
ENTRYPOINT ["java","-jar","/payment-service.jar"]

EXPOSE 8081