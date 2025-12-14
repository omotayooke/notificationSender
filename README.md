# Notification Sender Application
## Description
Reads a JSON file with `notificationUrl` and `notificationContent`, sends an HTTP POST request to the URL with the content, and logs URL, request/response bodies, status code, and response time.
Please follow below steps to have a successful run  

## Requirements
To run this program, you need the following installed and configured in your system path:

- Java 17 or higher https://docs.oracle.com/cd/E19182-01/820-7851/inst_cli_jdk_javahome_t/
- Maven (for building and testing) https://maven.apache.org/install.html

## Build
To build the program, open the directory where the program is, and type below command

mvn clean package

## Run
Run the program with the below command replacing values in "< >"
java -jar <target/notificationSender-1.0.jar> <path/to/notification.json>

## Test
To test the program, type below command
mvn clean test