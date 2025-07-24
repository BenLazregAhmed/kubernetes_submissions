package org.kubernetes.log_output;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/logOutput")
public class LogOutPutRestController {
    private final RandomStringService randomStringService;
    @GetMapping(path = "/status")
    public String status() {
        return randomStringService.status();
    }
}
