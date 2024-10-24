FROM openjdk:21
LABEL maintainer='emelyanovkr'
WORKDIR /app
COPY build/libs/weatherService-1.0.jar /app/weather-service.jar
CMD ["java", "-jar", "/app/weather-service.jar"]