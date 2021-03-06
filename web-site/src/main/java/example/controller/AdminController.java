package example.controller;

import example.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @GetMapping("/admin")
    public String getUsers(Model model) {
        model.addAttribute("allUsers", userService.getUsers());
        return "admin";
    }

    @PostMapping
    private String deleteUser(@RequestParam(defaultValue = "") Long id,
                              @RequestParam(defaultValue = "") String action) {
        if ("delete".equals(action)) {
            userService.deleteUser(id);
        }
        return "redirect:/";
    }
}
