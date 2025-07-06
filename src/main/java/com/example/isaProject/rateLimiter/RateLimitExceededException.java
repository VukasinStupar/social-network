package com.example.isaProject.rateLimiter;

public class RateLimitExceededException extends RuntimeException {
    public RateLimitExceededException(String message) {

        super(message);
    }
}

