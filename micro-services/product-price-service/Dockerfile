#FROM openjdk:8-jdk-alpine
FROM openjdk:11-slim
VOLUME /tmp
COPY target/*.jar product-price-service.jar
ENTRYPOINT ["java","-jar","/product-price-service.jar"]

EXPOSE 8081