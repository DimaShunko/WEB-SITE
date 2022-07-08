package example.controller;


import example.model.Topic;
import example.model.Task;
import example.repository.TopicRepository;
import example.repository.TaskRepository;
import example.repository.UserRepository;
import example.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.PreRemove;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class TaskController {


    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    @PostMapping("/task")
    public String addTask(@RequestParam String taskName, @RequestParam String userName,@RequestParam String topicName , RedirectAttributes redirectAttributes) {
        Topic topic = taskService.getListByNameAndUser(topicName, userName);

        redirectAttributes.addFlashAttribute("topicName", "topicTask");

        if(taskName.equals("")){
            redirectAttributes.addFlashAttribute("topicError2", "Поле пусто");
            redirectAttributes.addFlashAttribute("topic", topic);
            return "redirect:/main#openModal2";
        }

        if(taskRepository.findByName(taskName) != null){
            redirectAttributes.addFlashAttribute("topicError2", "Такое задание уже существует");
            redirectAttributes.addFlashAttribute("topic", topic);
            return "redirect:/main#openModal2";
        }

        Task task = new Task();
        task.setName(taskName);
        task.setData(new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date()));
        task.setTopic(topic);
        taskRepository.saveAndFlush(task);

        String url = "redirect:/main/" + topic.getId();

        return url;
    }

    @Transactional
    @PostMapping("/task/del")
    public String deleteTask(@RequestParam String name, @RequestParam String userName,@RequestParam String topicName, RedirectAttributes redirectAttributes) {
        taskRepository.deleteByName(name);
        Topic topic = taskService.getListByNameAndUser(topicName, userName);
        redirectAttributes.addFlashAttribute("topicName", "topicTask");

        String url = "redirect:/main/" + topic.getId();

        return url;
    }

    @PostMapping("/task/update")
    public String updateTask(@RequestParam String taskNameLast, @RequestParam String taskName, @RequestParam String userName,
                             @RequestParam String topicName , RedirectAttributes redirectAttributes){
        Topic topic = taskService.getListByNameAndUser(topicName, userName);
        Task task = taskRepository.findByNameAndTopic(taskNameLast, topic);
        redirectAttributes.addFlashAttribute("topicName", "topicTask");
        task.setName(taskName);
        task.setData(new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date()));
        taskRepository.save(task);

        String url = "redirect:/main/" + topic.getId();

        return url;

    }

}
