#FROM openjdk:8-jdk-alpine
FROM openjdk:11-slim
VOLUME /tmp
COPY target/*.jar inventory-service.jar
ENTRYPOINT ["java","-jar","/inventory-service.jar"]

EXPOSE 8081