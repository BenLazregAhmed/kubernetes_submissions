package org.kubernetes.logoutput;

import jakarta.annotation.PostConstruct;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class LogReaderService {

    private static final String FILE_PATH = "shared/ping-pong-log.txt";
    private String randomString;

    public String readLastLine() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
            return lines.isEmpty() ? "No data yet." : lines.get(lines.size() - 1);
        } catch (IOException e) {
            return "Error reading file: " + e.getMessage();
        }
    }
    @PostConstruct
    public void init() {
        randomString = UUID.randomUUID().toString();
    }

    @Scheduled(fixedRate = 5000)
    public void logRandomString() {
        String message = LocalDateTime.now() + " : " + randomString;
    }

    public String status() {
        return LocalDateTime.now() + " : " + randomString+"\nPing / Pong "+readLastLine();
    }

}

