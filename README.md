# README #

Meowlomo EMS (Execution Management System)

### Requirements ###

* Java 8
* Gradle 4.2.1+
* Docker host or Docker machine

### Building and executing the application from command line ###

```
mvn clean package
java -jar target/ems.jar
or
java -DappPort=8701 -jar target/ems.jar
```

Open http://<server_ip>:8080 in a browser
