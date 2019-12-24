FROM maven:3.6-jdk-8-alpine as build
ADD ./ app/
WORKDIR /app
RUN mvn clean install


FROM openjdk:8-jdk-alpine
COPY --from=build /app/target/serviceRegistry.jar serviceRegistry.jar
EXPOSE 9002
ENTRYPOINT ["java","-jar","-Dserver.port=9002","serviceRegistry.jar"]