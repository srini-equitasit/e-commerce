FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar product-price-service.jar
ENTRYPOINT ["java","-jar","/product-price-service.jar"]

EXPOSE 8081