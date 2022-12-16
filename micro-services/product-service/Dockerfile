FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar product-service.jar
ENTRYPOINT ["java","-jar","/product-service.jar"]

EXPOSE 8081