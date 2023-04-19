FROM openjdk:19
ADD target/dog-service-0.0.1-SNAPSHOT.jar dog-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","dog-service-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080