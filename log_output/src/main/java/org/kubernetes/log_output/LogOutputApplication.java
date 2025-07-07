package org.kubernetes.log_output;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LogOutputApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogOutputApplication.class, args);
    }

}
