FROM openjdk:11-jdk-slim
WORKDIR /app

COPY target/test_project.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
