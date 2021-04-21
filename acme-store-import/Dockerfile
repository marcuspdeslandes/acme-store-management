FROM openjdk:11.0.4-jre
ARG JAR_FILE=*.jar
COPY target/*.jar /acme-store-import.jar
ENTRYPOINT ["java", "-jar", "acme-store-import.jar"]