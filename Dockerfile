#
# this command will pull the latest openjdk 17 image
#FROM openjdk:17-jre-slim
FROM eclipse-temurin:17-jre


MAINTAINER usman

# this command tells the docker to pull the latest maven image present on this path
# and copy the jar file from the target directory to the root of the image
# and rename it to accounts-0.0.1-SNAPSHOT.jar
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# this command is kind of same of running the application using the command
#exwecute the jar file using java
ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]