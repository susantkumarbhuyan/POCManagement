package com.poc.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    @Async
    public CompletableFuture<String> asyncMethod() {
        // Some async logic
        return CompletableFuture.completedFuture("Async task completed");
    }
    
    @Async
    public void sendUpdateMail() {
        // Some async logic
      
    }
}