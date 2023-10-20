
## Running the application in dev mode

camel-quarkus-nats requires Java 17

```shell script
mvn quarkus:dev
```

End point: http://localhost:8080/hello

In IntelliJ you can attach a debugger to port 5005

## Build and run image

```shell script
mvn clean package
docker build -f src/main/docker/Dockerfile.jvm -t quarkus/kubernetes-quickstart-jvm .
docker run -i --rm -p 8080:8080 quarkus/kubernetes-quickstart-jvm
```