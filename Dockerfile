FROM openjdk:18.0.2.1-jdk as builder

WORKDIR /app/springboot-microservice-cars

COPY ./pom.xml /app
COPY ./springboot-microservice-cars/.mvn ./.mvn
COPY ./springboot-microservice-cars/mvnw .
COPY ./springboot-microservice-cars/pom.xml .

#RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
RUN ./mvnw dependency:go-offline
COPY ./springboot-microservice-cars/src ./src


RUN ./mvnw clean package -DskipTests

FROM openjdk:18.0.2.1-jdk

WORKDIR /app
RUN mkdir ./logs

COPY --from=builder /app/springboot-microservice-cars/target/springboot-microservice-cars-0.0.1-SNAPSHOT.jar .
EXPOSE 8001

CMD ["java", "-jar","springboot-microservice-cars-0.0.1-SNAPSHOT.jar"]