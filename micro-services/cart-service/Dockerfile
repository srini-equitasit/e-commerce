#FROM openjdk:8-jdk-alpine
FROM openjdk:11-slim
VOLUME /tmp
COPY target/*.jar cart-service.jar
ENTRYPOINT ["java","-jar","/cart-service.jar"]

EXPOSE 8081