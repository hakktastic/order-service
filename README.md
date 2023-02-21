# Order Service

This is a sample REST application generating and retrieving orders.

## Run in Docker

In order to run the application in Docker either download clone the repository and
your docker image, or pull it from Dockerhub.

### Build yourself

- $ git clone https://github.com/hakktastic/order-service
- $ mvn clean package
- $ docker build -t hakktastic/order-service:0.0.1 .
- $ docker run -p 8082:8082 -d hakktastic/order-service:0.0.1

### Pull Container image

- $ docker pull hakktastic/order-service:0.0.1
- $ docker run -p 8082:8082 -d hakktastic/order-service:0.0.1

## Service endpoints

Below you can find useful endpoints for the service.

- REST endpoint: [http://localhost:8082/api/orders](http://localhost:8082/api/orders)
- OpenAPI documentation: [http://localhost:8082/v3/api-docs/](http://localhost:8082/v3/api-docs/)
- Swagger UI: [http://localhost:8082/swagger-ui/index.html](http://localhost:8082/swagger-ui/index.html)
- Actuator: [http://localhost:8082/actuator/](http://localhost:8082/actuator/)