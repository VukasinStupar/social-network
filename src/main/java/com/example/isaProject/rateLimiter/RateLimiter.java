package com.example.isaProject.rateLimiter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiter {
    private final int maxRequests;
    private final long timeWindowInMillis;
    private final Map<String, List<Long>> userRequestTimestamps;

    public RateLimiter(int maxRequests, long timeWindowInMillis) {
        this.maxRequests = maxRequests;
        this.timeWindowInMillis = timeWindowInMillis;
        this.userRequestTimestamps = new ConcurrentHashMap<>();

    }

    public boolean allowRequest(String userId) {
        long currentTime = System.currentTimeMillis();
        boolean[] requestAdded = {false};

        userRequestTimestamps.compute(userId, (key, timestamps) -> {
            if (timestamps == null) {
                timestamps = new ArrayList<>();
            }

            timestamps.removeIf(timestamp -> currentTime - timestamp > timeWindowInMillis);

            if (timestamps.size() < maxRequests) {
                timestamps.add(currentTime);
                requestAdded[0] = true;
            }

            return timestamps;
        });

        return requestAdded[0];
    }
}