From openjdk:17
ADD target/blogg-app-docker.jar blogg-app-docker.jar
ENTRYPOINT ["java", "-jar", "blogg-app-docker.jar"]