# LLD Interview Prep — Spring Boot Scaffold

A ready-to-extend Spring Boot skeleton so you can jump straight into the actual
problem statement instead of setting up project plumbing.

## Run it

```bash
mvn spring-boot:run
```

Then check:

```bash
curl http://localhost:8080/api/v1/health
curl -X POST http://localhost:8080/api/v1/items -H "Content-Type: application/json" -d '{"name":"widget","price":9.99}'
curl http://localhost:8080/api/v1/items
```

## Package layout

```
com.interview.lld
├── controller      # REST endpoints (ItemController is a working CRUD example)
├── service         # interfaces + impl/ for business logic (in-memory store, no repository layer)
├── entity          # domain models — use Lombok (@Data, @Builder, @AllArgsConstructor, @RequiredArgsConstructor...)
├── dto             # request/response shapes, keep separate from entities
├── exception       # custom exceptions + GlobalExceptionHandler (@RestControllerAdvice)
├── common          # ApiResponse<T> generic wrapper
├── config          # @Configuration beans
└── client          # ExternalServiceClient — @Retry + @CircuitBreaker template for any downstream call
```

## Lombok

Already on the classpath — use `@Data`, `@Builder`, `@Getter/@Setter`,
`@RequiredArgsConstructor`, `@Slf4j`, etc. freely; annotation processing is
already wired through Maven.

## Retry & circuit breaker (Resilience4j)

`resilience4j-spring-boot3` + `spring-boot-starter-aop` are on the classpath,
with a `default` retry and circuit-breaker instance pre-configured in
[application.yml](src/main/resources/application.yml) (3 attempts with
exponential backoff; circuit opens at 50% failure rate over a 10-call window).

Annotate any method that calls an external system (another service, a
payment gateway, a third-party API) the same way
[client/ExternalServiceClient.java](src/main/java/com/interview/lld/client/ExternalServiceClient.java)
does:

```java
@Retry(name = "default", fallbackMethod = "fallback")
@CircuitBreaker(name = "default", fallbackMethod = "fallback")
public String call(String payload) { ... }

private String fallback(String payload, Throwable throwable) { ... }
```

Add a new named instance in `application.yml` under
`resilience4j.retry.instances.<name>` /
`resilience4j.circuitbreaker.instances.<name>` if a different call needs
different thresholds.

## Adapting to the actual problem

1. Rename/replace `Item` → your core entity (e.g. `ParkingSpot`, `Book`, `Seat`).
2. `ItemServiceImpl` holds state directly in a `ConcurrentHashMap` — swap the
   key/value types, or split into a repository layer yourself if the problem
   calls for one.
3. `GlobalExceptionHandler` + `ApiResponse<T>` already give you consistent
   error responses — just throw `ResourceNotFoundException` /
   `InvalidRequestException` from your service layer.
4. Apply `@Retry`/`@CircuitBreaker` to any new method that calls out to
   another system, following `ExternalServiceClient`.
