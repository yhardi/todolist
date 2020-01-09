FROM java:8
WORKDIR /app
COPY target/todolist-api-0.0.1-SNAPSHOT.jar /app/todolist-api.jar
ENTRYPOINT ["java","-jar","todolist-api.jar"]