FROM adoptopenjdk/openjdk8
MAINTAINER huqianghui <huqianghui0613@163.com>
VOLUME /temp
COPY target/eureka-server-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]