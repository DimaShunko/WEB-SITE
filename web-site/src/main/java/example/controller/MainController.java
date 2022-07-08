package example.controller;

import example.model.Topic;
import example.model.User;
import example.repository.TaskRepository;
import example.repository.TopicRepository;
import example.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;

    @Autowired
    private TopicRepository topicRepository;

    @GetMapping("/main")
    private String main(Model model, Principal principal, @RequestParam(name = "listName", defaultValue = "") String listName) {
        User user = userService.getUser(principal.getName());
        model.addAttribute("user", user);
        return "main";
    }

    @GetMapping("/main/{topicId}")
    private String mainWithTopicId(Model model, Principal principal, @PathVariable("topicId") Long topicId){
        User user = userService.getUser(principal.getName());

        model.addAttribute("user", user);

        Topic topic = topicRepository.findById(topicId).get();

        model.addAttribute("topic", topic);

        return "main";
    }
}

