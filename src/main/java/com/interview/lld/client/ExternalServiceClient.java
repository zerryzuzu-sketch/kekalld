package com.interview.lld.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Component;

// Template for calls to external systems — copy this shape, swap the body for the real client call.
@Component
public class ExternalServiceClient {

    @Retry(name = "default", fallbackMethod = "fallback")
    @CircuitBreaker(name = "default", fallbackMethod = "fallback")
    public String call(String payload) {
        return "ok:" + payload;
    }

    private String fallback(String payload, Throwable throwable) {
        return "fallback:" + payload;
    }
}
