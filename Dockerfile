FROM openjdk:17-alpine
LABEL maintainer="lucas.nouguier@protonmail.com"
VOLUME /main-app
ADD build/libs/api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "/app.jar"]