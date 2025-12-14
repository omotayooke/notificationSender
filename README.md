# Notification Sender Application
## Description
A simple Command Line Application that receives one parameter with the location of a json file, in the local disk, that contains the notification details.

it reads the JSON file with `notificationUrl` and `notificationContent`, sends an HTTP POST request to the URL with the content, and logs URL, request/response bodies, status code, and response time.

## Sample JSON Payload
```json
{
"notificationUrl":"https://webhook.site/df10f1b9-b18c-430e-b91f-c8635f7d83eb",
"notificationContent":{
"reportUID":"20fb8e02-9c55-410a-93a9-489c6f1d7598",
"studyInstanceUID":"9998e02-9c55-410a-93a9-489c6f789798"
    }
}
```
  
## Requirements
The following is required and must be installed to run this application on the system

- Java 17 or later https://docs.oracle.com/cd/E19182-01/820-7851/inst_cli_jdk_javahome_t/
- Maven (for building and testing) https://maven.apache.org/install.html

## Build
To build the program, open the directory where the program is, and type below command

`mvn clean package`

## Run
Run the program with the below command replacing values in "< >"

`java -jar <target/notificationSender-1.0.jar> <path/to/notification.json>`

## Test
To test the program, type below command

`mvn clean test`