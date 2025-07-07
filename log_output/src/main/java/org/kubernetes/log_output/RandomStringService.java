package org.kubernetes.log_output;

import jakarta.annotation.PostConstruct;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RandomStringService {

    private String randomString;

    @PostConstruct
    public void init() {
        randomString = UUID.randomUUID().toString();
        System.out.println("Generated random string: " + randomString);
    }

    @Scheduled(fixedRate = 5000)
    public void logRandomString() {
        System.out.println(LocalDateTime.now() + " : " + randomString);
    }
}

