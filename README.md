# Mars Explorer

Welcome to Yoshi's Mars Explorer! Have fun!

## API documentation
API doc at **http://localhost:8080/swagger-ui.html**

## Requests collection for Insomnia/Postman

[Download here](src/main/resources/static/rest-clients/mars-explorer.json)  

## Running

### Local Docker
docker build -t jyoshiriro/mars-explorer .

docker run -p 8080:8080 -t jyoshiriro/mars-explorer


### Docker Hub
./mvnw spring-boot:build-image