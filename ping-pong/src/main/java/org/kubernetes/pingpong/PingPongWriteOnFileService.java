package org.kubernetes.pingpong;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

@Service
public class PingPongWriteOnFileService {
    private final String FILE_PATH="shared/ping-pong-log.txt";
    private CounterRepo counterRepo;

    public PingPongWriteOnFileService(CounterRepo counterRepo) {
        this.counterRepo = counterRepo;
    }
    private void logToFile(String message) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
    public int getCounter() {
        Counter counter = counterRepo.findById(1).orElseGet(() -> counterRepo.save(new Counter(1, 0)));
        counter.setValue(counter.getValue()+1);
        counterRepo.save(counter);
        return counter.getValue();
    }
}
