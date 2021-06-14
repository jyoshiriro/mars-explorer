# Mars Explorer

Welcome to Yoshi's Mars Explorer! Have fun!

## API documentation
API doc at **http://localhost:8080/swagger-ui/index.html** 

## Running

### Local Docker
docker build -t jyoshiriro/mars-explorer .

docker run -p 8080:8080 -t jyoshiriro/mars-explorer


### Docker Hub
./mvnw spring-boot:build-image