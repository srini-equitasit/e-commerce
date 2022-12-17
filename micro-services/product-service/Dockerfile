#FROM openjdk:8-jdk-alpine
FROM openjdk:11-slim
VOLUME /tmp
COPY target/*.jar product-service.jar
ENTRYPOINT ["java","-jar","/product-service.jar"]

EXPOSE 8081