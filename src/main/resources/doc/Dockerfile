FROM java:alpine
RUN echo "Asia/Shanghai" > /etc/timezone
VOLUME /tmp
ADD SpringBootDemo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8994
ENTRYPOINT ["java","-jar","-Duser.timezone=GMT+8","/app.jar"]