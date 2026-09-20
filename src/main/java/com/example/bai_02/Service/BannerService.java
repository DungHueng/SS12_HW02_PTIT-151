package com.example.bai_02.Service;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BannerService {

    private static final Logger log =
            LoggerFactory.getLogger(BannerService.class);

    @CircuitBreaker(
            name = "bannerService",
            fallbackMethod = "bannerFallback"
    )
    public String getBanner(String userId) {

        // Giả lập Banner-Service bị lỗi
        throw new RuntimeException("Banner-Service đang DOWN");
    }

    private String bannerFallback(
            String userId,
            Throwable throwable
    ) {

        log.error(
                "Banner fallback - userId={}, exceptionType={}",
                userId,
                throwable.getClass().getName(),
                throwable
        );

        if (throwable instanceof IOException) {

            log.error("Lỗi mạng IOException");

        } else if (throwable instanceof CallNotPermittedException) {

            log.warn("Circuit Breaker đang OPEN");

        } else {

            log.error("Lỗi khác");

        }

        return "Freeship mọi đơn hàng";
    }
}