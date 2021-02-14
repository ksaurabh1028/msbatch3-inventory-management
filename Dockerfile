FROM openjdk:15
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} inventory-management.jar
ENTRYPOINT ["java","-jar","/inventory-management.jar"]
