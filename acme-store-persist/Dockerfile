FROM openjdk:11.0.4-jre
ARG JAR_FILE=*.jar
COPY target/*.jar /acme-store-persist.jar
ENTRYPOINT ["java", "-jar", "acme-store-persist.jar"]