package org.kubernetes.todo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewerController {

    @Value("${app.api.url}")
    private String apiUrl;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("apiUrl", apiUrl);
        return "index"; // corresponds to templates/index.html
    }
}
