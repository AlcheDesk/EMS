# EMS
**Execution Management System**

This is backend to manage virtual execution machines. It gets the test case info from ATM and mange and send execution task to VMC machins. 

**Development requirements:**

Java 8

Gradle



**Generate the executable Jar:**

You only need to run the boorJar Gradle task.
gragle bootJar

It will garatne a Jar named EMS_API.jar. Please refer to the Docker project to see the location where to put the jar file.

This project is ctrated and developed in Eclipse IDE.

Swagger address http://<server_ip>:8090 when the swagger i enabled in the application.yml
