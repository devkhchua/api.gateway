FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY target/*.jar api.gateway.jar
ENTRYPOINT ["java", "-jar", "/api.gateway.jar"]
EXPOSE 8080