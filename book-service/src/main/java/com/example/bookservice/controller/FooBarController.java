package com.example.bookservice.controller;

import io.github.resilience4j.bulkhead.BulkheadConfig;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Tag(name = "Foo Bar")
@RestController
@RequestMapping("book-service")
public class FooBarController {

    private final Logger logger = LoggerFactory.getLogger(FooBarController.class);
    private final ProcessTest processTest = new ProcessTest();

    private final io.github.resilience4j.bulkhead.Bulkhead bulkhead = io.github.resilience4j.bulkhead.Bulkhead.of("tryBulkhead", BulkheadConfig.custom()
            .maxConcurrentCalls(1)
            .build());

    @GetMapping("/foo-bar")
    @Operation(summary = "Foo Bar")
//    @Retry(name = "foo-bar", fallbackMethod = "fallbackMethod")
//    @CircuitBreaker(name = "default", fallbackMethod = "fallbackMethod")
//    @RateLimiter(name = "default")
    public String fooBar() throws InterruptedException {
        logger.info("Request to foo-bar is received!");

        if (Math.random() > 0.2) {
            throw new ArithmeticException();
        } else {
            return "Teste";
        }
    }

    @GetMapping("/foo-bar2")
    @Bulkhead(name = "tryBulkhead")
    public String fooBar2() throws InterruptedException {
        return "oi" + new Date();
    }

    public String fallbackMethod(ArithmeticException ex) {
        return "fallbackMethod foo-bar ArithmeticException!!!";
    }

    public String fallbackMethod(RuntimeException ex) {
        return "fallbackMethod foo-bar RuntimeException!!!";
    }

}
