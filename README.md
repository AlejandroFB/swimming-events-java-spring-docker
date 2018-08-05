# Swimmers competitions and events backend API

Backend API to provide functionality around a swimming competition system.

Main functionalities:
- Registration for swimmers and coaches
- Swimmers subscribe to competitions and participate in specific modalities
- Manage competitions
- Login system for both swimmers and coaches
- List swimmers, competitions, modalities, all of them with different filtering capabilities like showing future events
or the finished ones

Technology details:
- Stateless API ready to be consumed from both browser and mobiles clients
- Java 8
- Spring Security
- PostgreSQL
- Gradle
- Docker

Steps to run the application:
1. Run docker machine (docker quickstart terminal)
2. gradle clean build
3. Delete any existing docker image built before for the backend application
4. gradle buildDocker (builds the docker image with the spring boot app included)
5. docker-compose -f {your_local_path}/psv-zwemmen/src/main/resources/docker/docker-compose.yml up -d (runs both docker
images, postgres and backend java image)