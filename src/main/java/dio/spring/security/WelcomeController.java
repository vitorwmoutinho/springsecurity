package dio.spring.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WelcomeController {

    // Endpoint para a raiz "/api"
    @GetMapping
    public String welcome() {
        return "Welcome to My Spring Boot Web API";
    }

    // Endpoint para "/api/users"
    @GetMapping("/users")
    public String users() {
        return "Authorized user";
    }

    // Endpoint para "/api/managers"
    @GetMapping("/managers")
    public String managers() {
        return "Authorized manager";
    }
}
