#FROM openjdk:8-jdk-alpine
FROM openjdk:11-slim
VOLUME /tmp
COPY target/*.jar product-seller-service.jar
ENTRYPOINT ["java","-jar","/product-seller-service.jar"]

EXPOSE 8081