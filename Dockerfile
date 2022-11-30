## For Java 11, try this
#FROM adoptopenjdk/openjdk17:alpine-jre
#
## Refer to Maven build -> finalName
#ARG JAR_FILE=target/reporting-client-0.0.1-SNAPSHOT.jar
#
## cd /opt/app
#WORKDIR /opt/app
#
## cp target/spring-boot-web.jar /opt/app/app.jar
#COPY ${JAR_FILE} app.jar
#
## java -jar /opt/app/app.jar
#ENTRYPOINT ["java","-jar","app.jar"]

FROM adoptopenjdk/openjdk17:alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} /app/app.jar
EXPOSE 8083
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]