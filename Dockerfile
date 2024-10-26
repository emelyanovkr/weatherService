FROM openjdk:21
WORKDIR /app
COPY target/weatherService-1.0.jar /app/weather-service.jar
CMD ["java", "-jar", "/app/weather-service.jar"]