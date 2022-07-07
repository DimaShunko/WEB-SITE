package example.controller;


import example.model.List;
import example.model.Task;
import example.model.User;
import example.repository.ListRepository;
import example.repository.TaskRepository;
import example.repository.UserRepository;
import example.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class TaskController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    @PostMapping("/task")
    public String addTask(@RequestParam String taskName, @RequestParam String userName,@RequestParam String listName , RedirectAttributes redirectAttributes, Model model) {
        List list = taskService.getListByNameAndUser(listName, userName);



        redirectAttributes.addFlashAttribute("listName", "listTask");

        if(taskName.equals("")){
            redirectAttributes.addFlashAttribute("listError2", "Поле пусто");
            redirectAttributes.addFlashAttribute("list", list);
            return "redirect:/main#openModal2";
        }

        if(taskRepository.findByName(taskName) != null){
            redirectAttributes.addFlashAttribute("listError2", "Такое задание уже существует");
            redirectAttributes.addFlashAttribute("list", list);
            return "redirect:/main#openModal2";
        }

        Task task = new Task();
        task.setName(taskName);
        task.setData(new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date()));
        task.setList(list);
        taskRepository.saveAndFlush(task);

        System.out.println(list.getTasks());
        redirectAttributes.addFlashAttribute("list", list);

        return "redirect:/main";
    }

}
