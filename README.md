# quarkus-demo-api

A demo REST API built with Quarkus.

## Prerequisites

- Java 25 (latest LTS)
- Maven 3.9+

## Running in Dev Mode

```shell
./mvnw quarkus:dev
```

The API will be available at http://localhost:8080.

## Endpoints

| Method | Path                     | Description                               |
| ---    | ---                      | ---                                       |
| GET    | /api/v1/hello            | Hello World                               |
| GET    | /api/v1/hello/{name}     | Hello with name param                     |
| GET    | /api/v1/stress/{seconds} | Burn CPU for N seconds (query: threads=1) |

## Testing

```shell
./mvnw test
```

## Building

```shell
./mvnw package
```

## Container Image

Image: `quay.io/stephennimmo/quarkus-demo-api`

```shell
./mvnw package
podman build -t quay.io/stephennimmo/quarkus-demo-api .
podman push quay.io/stephennimmo/quarkus-demo-api
```
