package example.controller;

import example.model.Topic;
import example.model.Task;
import example.repository.TopicRepository;
import example.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class TaskController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/task")
    public String addTask(@RequestParam String taskName, @RequestParam Long topicId, RedirectAttributes redirectAttributes) {
        Topic topic = topicRepository.findById(topicId).get();

        redirectAttributes.addFlashAttribute("topicName", "topicTask");

        if(taskName.equals("")){
            redirectAttributes.addFlashAttribute("topicError2", "Поле пусто");
            redirectAttributes.addFlashAttribute("topic", topic);
            return "redirect:/main#openModal2";
        }

        if(taskRepository.findByNameAndTopic(taskName, topic) != null){
            redirectAttributes.addFlashAttribute("topicError2", "Такая задача уже существует");
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

    @PostMapping("/task/del")
    public String deleteTask(@RequestParam Long taskId, @RequestParam Long topicId, RedirectAttributes redirectAttributes) {

        taskRepository.deleteById( taskId);
        Topic topic = topicRepository.findById(topicId).get();

        redirectAttributes.addFlashAttribute("topicName", "topicTask");

        String url = "redirect:/main/" + topic.getId();

        return url;
    }

    @PostMapping("/task/update/{id}")
    public String updateTask(@PathVariable("id") Long id, @RequestParam String taskName, @RequestParam Long topicId, RedirectAttributes redirectAttributes){
        Topic topic = topicRepository.findById(topicId).get();
        Task task = taskRepository.findById(id).get();

        String url;

        redirectAttributes.addFlashAttribute("topicName", "topicTask");

        if(taskName.equals("")){
            redirectAttributes.addFlashAttribute("topicError3", "Поле пусто");
            redirectAttributes.addFlashAttribute("topic", topic);
            url = "redirect:/main#" + task.getId();
            return url;
        }

        if(taskRepository.findByNameAndTopic(taskName, topic) != null){
            redirectAttributes.addFlashAttribute("topicError3", "Такая задача уже существует");
            redirectAttributes.addFlashAttribute("topic", topic);
            url = "redirect:/main#" + task.getId();
            return url;
        }

        task.setName(taskName);
        task.setData(new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date()));
        taskRepository.save(task);

        url = "redirect:/main/" + topic.getId();

        return url;
    }

    @PostMapping("/task/status")
    public String changeStatus(@RequestParam Long topicId, @RequestParam Long taskId,
                                RedirectAttributes redirectAttributes){

        Topic topic = topicRepository.findById(topicId).get();
        Task task = taskRepository.findById(taskId).get();

        redirectAttributes.addFlashAttribute("topicName", "topicTask");

        if(!task.isStatus()){
            task.setStatus(true);
        }else {
            task.setStatus(false);
        }
        taskRepository.save(task);

        String url = "redirect:/main/" + topic.getId();

        return url;

    }

}
