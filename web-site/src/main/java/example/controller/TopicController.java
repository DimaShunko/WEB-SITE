package example.controller;

import example.model.Topic;
import example.model.User;
import example.repository.BasketRepository;
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

    @Autowired
    private BasketRepository basketRepository;

    @PostMapping("/topic")
    public String addList(@RequestParam String topic, @RequestParam Long userId, RedirectAttributes redirectAttributes) {

        User user = userRepository.findById(userId).get();

        if(topic.equals("")){
            redirectAttributes.addFlashAttribute("topicError", "Поле пусто");
            return "redirect:/main#openModal";
        }

        if(topicRepository.findByNameAndUser(topic, user) != null){
            redirectAttributes.addFlashAttribute("topicError", "Такой список уже существует");
            return "redirect:/main#openModal";
        }

        Topic topics = new Topic();
        topics.setName(topic);
        topics.setUser(user);
        topicRepository.save( topics);

        redirectAttributes.addFlashAttribute("topicName", "topicTask");

        String url = "redirect:/main/" + topics.getId();

        return url;
    }

    @PostMapping("/topic/del")
    @Transactional
    public String deleteList(@RequestParam Long topicId, @RequestParam String topicName,
                             @RequestParam Long userId) {

        topicRepository.deleteById(topicId);

        User user = userRepository.findById(userId).get();

        basketRepository.deleteByTopicNameAndUser(topicName, user);

        return "redirect:/main";
    }

    @PostMapping("/topic/show")
    public String showListTask(@RequestParam Long topicId,RedirectAttributes redirectAttributes) {

        Topic topic = topicRepository.findById(topicId).get();


        redirectAttributes.addFlashAttribute("topicName", "topicTask");

        String url = "redirect:/main/" + topic.getId();

        return url;
    }

}
