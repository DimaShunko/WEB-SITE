package example.controller;

import example.model.List;
import example.model.Task;
import example.model.User;
import example.repository.ListRepository;
import example.repository.TaskRepository;
import example.repository.UserRepository;
import example.service.TaskService;
import example.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;

    @GetMapping("/main")
    private String main(Model model, Principal principal, @RequestParam(name = "listName", defaultValue = "") String listName) {
        User user = userService.getUser(principal.getName());
        model.addAttribute("user", user);
        return "main";
    }
}

