package com.example.bookservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;

@Bulkhead(name = "tests")
public class ProcessTest {

    public String process1() throws InterruptedException {
        Thread.sleep(5000);
        return "process1";
    }

    public String process2() throws InterruptedException {
        Thread.sleep(5000);
        return "process2";
    }
}
