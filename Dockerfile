FROM maven:3.8.5-eclipse-temurin-17 AS build
COPY src /usr/app/src
COPY pom.xml /usr/app
RUN mvn -f /usr/app/pom.xml clean package

FROM openjdk:17-alpine
COPY --from=build /usr/app/target/mock-error.jar /usr/app/
ENTRYPOINT java -Xmx192m -jar /usr/app/mock-error.jar
EXPOSE 8080/tcp