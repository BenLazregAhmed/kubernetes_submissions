package org.kubernetes.pingpong;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class PongRestController {
    private final PingPongWriteOnFileService pingPongWriteOnFileService;
    @GetMapping("/pingpong")
    public String pingpong() {
        return "pong"+pingPongWriteOnFileService.getCounter();
    }
    @GetMapping("ping")
    public int ping() {
        return pingPongWriteOnFileService.getCounter();
    }
}
