package org.kubernetes.pingpong;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
@Service
public class PingPongWriteOnFileService {
    private final String FILE_PATH="shared/ping-pong-log.txt";
    private int counter=-1;
    private void logToFile(String message) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
    public int getCounter() {
        counter++;
        return counter;
    }
}
