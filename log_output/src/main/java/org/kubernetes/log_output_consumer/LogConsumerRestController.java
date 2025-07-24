package org.kubernetes.log_output_consumer;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/logOutput")
public class LogConsumerRestController {

    private final LogReaderService logReaderService;

    @GetMapping("/status")
    public String getStatus() {
        return logReaderService.status();
    }
}

