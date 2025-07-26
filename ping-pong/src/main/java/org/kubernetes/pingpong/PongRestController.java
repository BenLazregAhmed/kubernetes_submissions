package org.kubernetes.pingpong;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pingpong")
@RequiredArgsConstructor
public class PongRestController {
    private final PingPongWriteOnFileService pingPongWriteOnFileService;
    @GetMapping("")
    public String pingpong() {
        return "pong"+pingPongWriteOnFileService.getCounter();
    }
}
