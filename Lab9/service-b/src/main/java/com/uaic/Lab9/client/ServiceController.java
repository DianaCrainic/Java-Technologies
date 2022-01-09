package com.uaic.Lab9.client;

import org.eclipse.microprofile.faulttolerance.*;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@ApplicationScoped
@Path("/hello")
public class ServiceController {
    @GET
    public String hello() {
        return "Hello World";
    }

    private String fallback() {
        return "Fallback answer";
    }

    private String timeoutFallback() {
        return "Fallback answer due to timeout";
    }

    private String bulkheadThreadPoolFallback() {
        return "Fallback answer";
    }

    /*
        Timeout
    */
    @GET
    @Path("/fallback")
    @Fallback(fallbackMethod = "fallback")
    public String serviceFallback() throws Throwable {
        throw new Throwable("Fallback Error");
    }

    /*
        Timeout Fallback
    */
    @GET
    @Path("/timeout-fallback")
    @Fallback(fallbackMethod = "timeoutFallback")
    @Timeout(500)
    public String serviceTimeoutFallback() throws Throwable {
        Thread.sleep(1000);
        return "Hello World";
    }

    /*
        Retry Fallback
    */
    @GET
    @Path("/retry-fallback")
    @Fallback(fallbackMethod = "fallback")
    @Retry(maxRetries = 2, delay = 200, jitter = 50)
    public String serviceRetryFallback() throws Throwable {
        throw new Throwable("Retry Fallback Error");
    }

    /*
        Circuit-Breaker
    */
    @GET
    @Path("/circuit-breaker")
    @Fallback(fallbackMethod = "fallback")
    @CircuitBreaker(
            successThreshold = 10,
            requestVolumeThreshold = 4,
            failureRatio = 0.75,
            delay = 1000)
    public String serviceCircuitBreaker() throws Throwable {
        throw new Throwable("Circuit Breaker Error");
    }

    /*
        Bulkhead Thread-Pool
    */
    @GET
    @Path("/bulkhead-thread-pool")
    @Bulkhead(value = 5, waitingTaskQueue = 8)
    public String serviceBulkheadThreadPool() {
        return "Hello World";
    }

    /*
        Semaphore
    */
    @GET
    @Path("/semaphore")
    @Fallback(fallbackMethod = "fallback")
    @Bulkhead(5)
    public String serviceSemaphore() {
        return "Hello World";
    }
}
