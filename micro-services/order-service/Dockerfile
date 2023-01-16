#FROM openjdk:8-jdk-alpine
FROM openjdk:11-slim
VOLUME /tmp
COPY target/*.jar order-service.jar
ENTRYPOINT ["java","-jar","/order-service.jar"]

EXPOSE 8081