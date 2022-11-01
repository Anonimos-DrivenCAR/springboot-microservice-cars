FROM openjdk:18.0.1.1-jdk

WORKDIR /app

COPY ./target/springboot-microservice-cars-0.0.1-SNAPSHOT.jar .

EXPOSE 8001

ENTRYPOINT ["java", "-jar","springboot-microservice-cars-0.0.1-SNAPSHOT.jar"]