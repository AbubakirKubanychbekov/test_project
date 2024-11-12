FROM openjdk:11-jdk-slim
WORKDIR /app

COPY target/test_project.jar app.jar
CMD ["java", "-jar", "app.jar"]
EXPOSE 8080
