package org.kubernetes.log_output;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RandomStringService {
    private final String FILE_PATH="shared/random-string-log.txt";
    private String randomString;

    @PostConstruct
    public void init() {
        randomString = UUID.randomUUID().toString();
        logToFile("Generated random string: " + randomString);
    }

    @Scheduled(fixedRate = 5000)
    public void logRandomString() {
        String message = LocalDateTime.now() + " : " + randomString;
        logToFile(message);
    }

    public String status() {
        return LocalDateTime.now() + " : " + randomString;
    }

    private void logToFile(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}
