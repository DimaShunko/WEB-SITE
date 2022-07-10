package example;

import example.model.User;
import example.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebSiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSiteApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {

            userService.saveUser(new User( "ADMIN", "admin", "admin", true, "ROLE_ADMIN"));

        };
    }

}
