package org.kubernetes.log_output_consumer;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class LogReaderService {

    private static final String FILE_PATH = "shared/random-string-log.txt";

    public String readLastLine() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
            return lines.isEmpty() ? "No data yet." : lines.get(lines.size() - 1);
        } catch (IOException e) {
            return "Error reading file: " + e.getMessage();
        }
    }
}

