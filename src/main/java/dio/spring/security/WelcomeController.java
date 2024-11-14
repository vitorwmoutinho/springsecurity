package dio.spring.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class WelcomeController {

    // Endpoint público acessível a todos
    @GetMapping
    public String welcome() {
        return "Welcome to My Spring Boot Web API";
    }

    // Endpoint acessível para usuários com a role 'USERS' ou 'MANAGERS'
    @GetMapping("/users")
    public String users() {
        return "Authorized user";
    }

    // Endpoint acessível apenas para usuários com a role 'MANAGERS'
    @GetMapping("/managers")
    public String managers() {
        return "Authorized manager";
    }
}
