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

| Method | Path                | Description            |
| ---    | ---                 | ---                    |
| GET    | /api/v1/hello       | Hello World            |
| GET    | /api/v1/hello/{name} | Hello with name param |

## Building

```shell
./mvnw package
```

## Building a Container Image

```shell
./mvnw package
podman build -f src/main/docker/Containerfile.jvm -t quarkus/quarkus-demo-api-jvm .
```
