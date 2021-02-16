FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} inventory-management.jar
ENTRYPOINT ["java","-jar","/inventory-management.jar"]

EXPOSE 2222
