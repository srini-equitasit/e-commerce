#FROM openjdk:8-jdk-alpine
FROM openjdk:11-slim
VOLUME /tmp
COPY target/*.jar user-service.jar
ENTRYPOINT ["java","-jar","/user-service.jar"]

EXPOSE 8081