FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar product-seller-service.jar
ENTRYPOINT ["java","-jar","/product-seller-service.jar"]

EXPOSE 8081