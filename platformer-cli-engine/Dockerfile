FROM maven AS builder
COPY . .
RUN mvn clean install

#FROM alpine/java:21-jdk
#RUN apk update
#RUN apk add libx11 libxtst libxkbcommon-x11 libxext libxtst libxt libxinerama
#COPY --from="builder" target/platformer-cli-engine-1.0-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

FROM ubuntu
RUN apt-get update && \
    DEBIAN_FRONTEND=noninteractive \
    apt-get -y install default-jre-headless && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

RUN apt-get update

RUN apt-get install -y \
    libx11-6 \
    libxext6 \
    libxi6 \
    libxrandr2 \
    libxtst6 \
    libxinerama1 \
    libxcursor1 \
    libxft2 \
    libxkbcommon-x11-0 \
    libx11-xcb1 \
    libxt6

COPY --from="builder" target/platformer-cli-engine-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]

