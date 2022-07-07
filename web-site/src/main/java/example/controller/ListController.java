package example.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import example.model.List;
import example.model.Task;
import example.model.User;
import example.repository.ListRepository;
import example.repository.TaskRepository;
import example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class ListController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ListRepository listRepository;


    @PostMapping("/list")
    public String addList(@RequestParam String list, @RequestParam String name , RedirectAttributes redirectAttributes) {

        if(list.equals("")){
            redirectAttributes.addFlashAttribute("listError", "Поле пусто");
            return "redirect:/main#openModal";
        }

        if(listRepository.findByName(list) != null){
            redirectAttributes.addFlashAttribute("listError", "Такой список уже существует");
            return "redirect:/main#openModal";
        }
        User user = userRepository.findByName(name);

        List lists = new List();
        lists.setName(list);
        lists.setUser(user);
        listRepository.save(lists);

        return "redirect:/main";
    }

    @PostMapping("/list/del")
    @Transactional
    public String deleteList(@RequestParam String name) {
        listRepository.deleteByName(name);

        return "redirect:/main";
    }

    @PostMapping("/list/show")
    public String showListTask(@RequestParam String listName, @RequestParam String userName, RedirectAttributes redirectAttributes) {
        User user = userRepository.findByName(userName);
        List list = listRepository.findByNameAndUser(listName,user);
        redirectAttributes.addFlashAttribute("list", list);
        redirectAttributes.addFlashAttribute("listName", "listTask");
        return "redirect:/main";
    }

}
