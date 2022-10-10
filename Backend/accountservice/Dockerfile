FROM maven:3.6.3-jdk-11-openj9

COPY target/accountservice-0.0.1-SNAPSHOT.jar accountservice.jar

ENTRYPOINT ["java", "-jar", "/accountservice.jar"]