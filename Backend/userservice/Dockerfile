FROM maven:3.6.3-jdk-11-openj9

COPY target/userservice-0.0.1-SNAPSHOT.jar userservice.jar

ENTRYPOINT ["java", "-jar", "/userservice.jar"]