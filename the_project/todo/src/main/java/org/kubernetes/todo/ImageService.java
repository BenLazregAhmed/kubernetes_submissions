package org.kubernetes.todo;

import jakarta.annotation.PostConstruct;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.*;
import java.time.Duration;
import java.time.Instant;

@Service
public class ImageService {

    private static final String IMAGE_URL = "https://picsum.photos/1200";
    private static final Path IMAGE_DIR = Paths.get("/app/images");
    private static final Path CURRENT_IMAGE = IMAGE_DIR.resolve("current.jpg");
    private static final Path TIMESTAMP_FILE = IMAGE_DIR.resolve("timestamp.txt");

    private static final Duration VALID_DURATION = Duration.ofMinutes(10);

    /**
     * Run once on application startup
     */
    @PostConstruct
    public void init() throws IOException {
        Files.createDirectories(IMAGE_DIR);

        if (!Files.exists(CURRENT_IMAGE)) {
            System.out.println("Image not found on startup. Fetching new image...");
            fetchAndStoreImage();
        } else {
            System.out.println("Existing image found. Skipping fetch.");
        }
    }

    /**
     * Run every 10 minutes
     */
    @Scheduled(fixedRate = 10 * 60 * 1000)
    public void refreshImageIfExpired() throws IOException {
        if (isImageExpired()) {
            System.out.println("Image expired. Fetching new image...");
            fetchAndStoreImage();
        } else {
            System.out.println("Image still valid. No need to refresh.");
        }
    }

    /**
     * Serve image bytes from the cache
     */
    public byte[] getImage() throws IOException {
        return Files.readAllBytes(CURRENT_IMAGE);
    }

    /**
     * Check if image is expired
     */
    private boolean isImageExpired() {
        try {
            if (!Files.exists(TIMESTAMP_FILE)) return true;
            Instant lastFetch = Instant.parse(Files.readString(TIMESTAMP_FILE).trim());
            Duration elapsed = Duration.between(lastFetch, Instant.now());
            return elapsed.compareTo(VALID_DURATION) > 0;
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * Download and save the image and timestamp
     */
    private void fetchAndStoreImage() throws IOException {
        try (InputStream in = new URL(IMAGE_URL).openStream()) {
            Files.copy(in, CURRENT_IMAGE, StandardCopyOption.REPLACE_EXISTING);
            Files.writeString(TIMESTAMP_FILE, Instant.now().toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
    }
}

