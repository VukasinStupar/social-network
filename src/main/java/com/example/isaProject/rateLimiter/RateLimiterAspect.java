package com.example.isaProject.rateLimiter;

import com.example.isaProject.dto.CommentDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RateLimiterAspect {
    private final RateLimiter rateLimiter;

    public RateLimiterAspect() {

        this.rateLimiter = new RateLimiter(5, 60_000);
    }

    @Around("execution(* com.example.OnlyBuns.service.CommentService.create(..)) && args(commentDto)")
    public Object enforceRateLimit(ProceedingJoinPoint joinPoint, CommentDto commentDto) throws Throwable {
        String userId = commentDto.getUserId().toString();

        if (!rateLimiter.allowRequest(userId)) {
            throw new RateLimitExceededException("Rate limit exceeded. You can only create 5 comments per minute.");
        }

        return joinPoint.proceed();
    }
}