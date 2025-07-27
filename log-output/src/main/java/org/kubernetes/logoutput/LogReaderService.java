package org.kubernetes.logoutput;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LogReaderService {

    private static final String FILE_PATH = "shared/ping-pong-log.txt";
    private String randomString;
    private final RestTemplate restTemplate;
    @Value("${ping.host}")
    private String HOST;
    @Value("${ping.port}")
    private int PORT;

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
        return LocalDateTime.now() + " : " + randomString+"\nPing / Pong "+getPings();
    }

    private int getPings() {
        String url = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(HOST)
                .port(PORT)
                .path("/ping")
                .toUriString();

        try {
            return restTemplate.getForObject(url, Integer.class);
        } catch (Exception e) {
            // handle failure (log, rethrow, fallback value, etc.)
            e.printStackTrace();
            return -1; // or throw new RuntimeException("Ping failed")
        }
    }
}

