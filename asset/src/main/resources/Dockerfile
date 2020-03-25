FROM openjdk:8-jre-alpine
RUN mkdir -p  /tmp/app
ADD /target/asset.jar /tmp/app/asset.jar
ENTRYPOINT ["java","-jar", "/tmp/app/asset.jar"]
EXPOSE 8761