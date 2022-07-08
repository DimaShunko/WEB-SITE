package example.controller;

import example.model.Topic;
import example.model.User;
import example.repository.TopicRepository;
import example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class TopicController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TopicRepository topicRepository;


    @PostMapping("/topic")
    public String addList(@RequestParam String topic, @RequestParam String name , RedirectAttributes redirectAttributes) {

        if(topic.equals("")){
            redirectAttributes.addFlashAttribute("topicError", "Поле пусто");
            return "redirect:/main#openModal";
        }

        if(topicRepository.findByName(topic) != null){
            redirectAttributes.addFlashAttribute("topicError", "Такой список уже существует");
            return "redirect:/main#openModal";
        }
        User user = userRepository.findByName(name);

        Topic topics = new Topic();
        topics.setName(topic);
        topics.setUser(user);
        topicRepository.save( topics);

        return "redirect:/main";
    }

    @PostMapping("/topic/del")
    @Transactional
    public String deleteList(@RequestParam String name) {
        topicRepository.deleteByName(name);

        return "redirect:/main";
    }

    @PostMapping("/topic/show")
    public String showListTask(@RequestParam String topicName, @RequestParam String userName, RedirectAttributes redirectAttributes) {
        User user = userRepository.findByName(userName);
        Topic topic = topicRepository.findByNameAndUser(topicName,user);
//        redirectAttributes.addFlashAttribute("topic", topic);
        redirectAttributes.addFlashAttribute("topicName", "topicTask");
        String url = "redirect:/main/" + topic.getId();

        return url;
    }

}
