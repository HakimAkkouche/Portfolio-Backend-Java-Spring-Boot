FROM amazoncorretto:17
WORKDIR /app
COPY target/*.jar app.jar
COPY certs/ /app/certs
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
