FROM openjdk:8-jre-alpine
RUN mkdir -p  /tmp/app
ADD /target/registry.jar /tmp/app/registry.jar
CMD java -jar /tmp/app/registry.jar
EXPOSE 8761
