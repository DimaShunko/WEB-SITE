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

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ListRepository listRepository;

    @Autowired
    private TaskRepository taskRepository;

    private List listView;

    @Autowired
    private TaskService taskService;

    @GetMapping("/main")
    private String main(Model model, Principal principal, @RequestParam(name = "listName", defaultValue = "") String listName) {
        User user = userService.getUser(principal.getName());
        model.addAttribute("user", user);
//        model.addAttribute("list", listView);
//        if(!listName.equals("")){
//            System.out.println(listName);
//            model.addAttribute("listName", listName);
//        }
//        System.out.println(listName);
        return "main";
    }

//    @PostMapping("/task")
//    public String addTask(@RequestParam String taskName, @RequestParam String userName,@RequestParam String listName , RedirectAttributes redirectAttributes, Model model) {
////        User user = userRepository.findByName(userName);
////        List list = listRepository.findByNameAndUser(listName,user);
//
//
//
//        redirectAttributes.addFlashAttribute("listName", "listTask");
//
////        if(taskName.equals("")){
////            redirectAttributes.addFlashAttribute("listError2", "Поле пусто");
////            redirectAttributes.addFlashAttribute("list", list);
////            return "redirect:/main#openModal2";
////        }
////
////        if(taskRepository.findByName(taskName) != null){
////            redirectAttributes.addFlashAttribute("listError2", "Такое задание уже существует");
////            redirectAttributes.addFlashAttribute("list", list);
////            return "redirect:/main#openModal2";
////        }
//
//        List list = taskService.getListByNameAndUser(listName, userName);
//
//        Task task = new Task();
//        task.setName(taskName);
//        task.setData(new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date()));
//        task.setList(list);
//        taskRepository.saveAndFlush(task);
//
//        System.out.println(list.getTasks());
////        model.addAttribute("listName", "listTask");
////        redirectAttributes.addFlashAttribute("list", list);
//        listView = list;
//
//
//        return "redirect:/main";
//    }

//    @PostMapping("/list/show")
//    public String showListTask(@RequestParam String listName, @RequestParam String userName, Model model) {
//        User user = userRepository.findByName(userName);
//        List list = listRepository.findByNameAndUser(listName,user);
//        listView = list;
//        model.addAttribute("listName", "listTask");
//        return "redirect:/main";
//    }

}

