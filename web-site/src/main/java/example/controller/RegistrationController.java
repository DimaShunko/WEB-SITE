package example.controller;

import example.model.User;
import example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") User userForm, Model model) {

        if(userForm.getName().equals("")){
            model.addAttribute("usernameError",
                    "Введите имя");
            return "registration";
        }

        Pattern p = Pattern.compile("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}");
        Matcher m = p.matcher(userForm.getUsername());
        if(!m.matches()){
            model.addAttribute("usernameError",
                    "Неккоректно введен email");
            return "registration";
        }

        if(userForm.getPassword().equals("")){
            model.addAttribute("usernameError",
                    "Введите пароль");
            return "registration";
        }

        if (!userForm.getPassword().equals(userForm.getConfirmPassword())) {

            model.addAttribute("usernameError",
                    "Введенные пароли не совпадают");
            return "registration";
        }

        userForm.setActive(true);
        userForm.setRole("ROLE_USER");
        if (!userService.saveUser(userForm)) {
            model.addAttribute("usernameError",
                    "Пользователь с таким именем уже существует");
            return "registration";
        }
        return "redirect:/login";
    }
}