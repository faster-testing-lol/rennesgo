FROM maven:3.6.2-jdk-11 AS builder
COPY . .
RUN mvn package

FROM openjdk:13
COPY --from=builder target/rennesgo-backend-*.jar server.jar
EXPOSE 80
CMD java -jar server.jar --server.port=80
