package example.controller;

import example.model.Task;
import example.model.Topic;
import example.model.User;
import example.repository.BasketRepository;
import example.repository.TaskRepository;
import example.repository.TopicRepository;
import example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class BasketController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private BasketRepository basketRepository;

    @PostMapping("/basket/show")
    public String showBasket( RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("topicName", "Basket");

        return "redirect:/main";
    }

    @PostMapping("/basket/del")
    public String deleteBasket(@RequestParam Long basketId, RedirectAttributes redirectAttributes) {
        basketRepository.deleteById(basketId);

        redirectAttributes.addFlashAttribute("topicName", "Basket");

        return "redirect:/main";
    }

    @PostMapping("/basket/return")
    public String returnBasket(@RequestParam Long basketId, @RequestParam String topicName,
                               @RequestParam String taskName, @RequestParam Long userId, RedirectAttributes redirectAttributes) {
        basketRepository.deleteById(basketId);

        User user = userRepository.findById(userId).get();
        Topic topic = topicRepository.findByNameAndUser(topicName, user);

        Task task = new Task();
        task.setName(taskName);
        task.setData(new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date()));
        task.setTopic(topic);
        taskRepository.saveAndFlush(task);

        redirectAttributes.addFlashAttribute("topicName", "Basket");

        return "redirect:/main";
    }
}
