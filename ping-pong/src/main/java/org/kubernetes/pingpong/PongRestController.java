package org.kubernetes.pingpong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pingpong")
public class PongRestController {
    private int count = -1;
    @GetMapping("/")
    public String pingpong() {
        count++;
        return "pong"+count;
    }
}
