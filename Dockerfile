FROM openjdk:22-ea-11-jdk-slim

EXPOSE 8081

ADD target/SpringBoot-0.0.1-SNAPSHOT.jar myapp.jar

ENTRYPOINT ["java","-jar","/myapp.jar"]