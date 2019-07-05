FROM openjdk:8-jdk-alpine
MAINTAINER yusufsaheedtaiwo@gmail.com
COPY /target/jumia-assessment-0.0.1-SNAPSHOT.jar jumia-assessment-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "jumia-assessment-0.0.1-SNAPSHOT.jar"]
VOLUME /tmp
EXPOSE 8888