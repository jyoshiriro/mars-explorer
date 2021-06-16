# Mars Explorer

Welcome to Yoshi's Mars Explorer! Have fun!

## Examples of calls

### Example 1: In a 5x5 plan, insert a space probe in 1,2,N and move LMLMLMLMM
```sh
curl --location --request POST 'http://localhost:8080/plane' \
--header 'Content-Type: application/json' \
--data-raw '{
    "width": 5,
    "height": 5
}'
```

```sh
curl --location --request POST 'http://localhost:8080/probes' \
--header 'Content-Type: application/json' \
--data-raw '{
    "x": 1,
    "y": 2,
    "direction": "N"
}'
```

```sh
curl --location --request POST 'http://localhost:8080/probes/1/move/LMLMLMLMM'
```

**Result**

```json
{
  "direction":"N",
  "x":1,
  "y":3,
  "plane":{"width":5,"height":5}
}
```

### Example 2: In a 5x5 plan, insert a space probe in 3,3,E and move MMRMMRMRRM
```sh
curl --location --request POST 'http://localhost:8080/plane' \
--header 'Content-Type: application/json' \
--data-raw '{
    "width": 5,
    "height": 5
}'
```

```sh
curl --location --request POST 'http://localhost:8080/probes' \
--header 'Content-Type: application/json' \
--data-raw '{
    "x": 3,
    "y": 3,
    "direction": "E"
}'
```

```sh
curl --location --request POST 'http://localhost:8080/probes/2/move/MMRMMRMRRM'
```

**Result**

```json
{
  "direction":"E",
  "x":5,
  "y":1,
  "plane":{"width":5,"height":5}
}
```

## API documentation
API doc at **http://localhost:8080/swagger-ui.html**

## Requests collection for Insomnia/Postman

Get from GitHub **[here](src/main/resources/static/rest-clients/mars-explorer.json)**

Get from API at **http://localhost:8080/rest-clients/mars-explorer.json**  

## Running

### Local run
./mvnw spring-boot:run

### Local Docker
docker build -t jyoshiriro/mars-explorer .

docker run -p 8080:8080 -t jyoshiriro/mars-explorer

### Docker Hub
./mvnw spring-boot:build-image