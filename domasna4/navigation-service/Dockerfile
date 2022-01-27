FROM maven:3.8.4-openjdk-11-slim

WORKDIR /app

COPY . /app

RUN mvn compile && mvn package

ENTRYPOINT ["java","-jar","/app/target/navigation-service.jar"]