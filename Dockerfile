# Docker-Dropwizard-Gradle example
# VERSION 0.1

# the base image is a trusted ubuntu build with java 7 (https://index.docker.io/u/dockerfile/java/)
FROM dockerfile/java

MAINTAINER Kyle Boon, kyle.f.boon@gmail.com

# we need this because the workdir is modified in dockerfile/java
WORKDIR /app

# run the (java) server as the daemon user
USER daemon

# copy the files over to the container
ADD build/libs/dropwizard-gradle-docker-0.1.0-SNAPSHOT-all.jar /app/contact-dropwizard.jar
ADD dev_config.yml /app/dev_config.yml
ADD contact_db.h2.db /app/contact_db.h2.db

# run the server when a container based on this image is being run
CMD ["java", "-jar", "/app/contact-dropwizard.jar", "server", "/app/dev_config.yml"]
# ENTRYPOINT "java"

# the server binds to 8080 - expose that port
EXPOSE 4080