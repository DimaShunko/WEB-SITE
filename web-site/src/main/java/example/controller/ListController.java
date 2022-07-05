package example.controller;

import example.model.List;
import example.repository.ListRepository;
import example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ListController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ListRepository listRepository;

    @PostMapping("/list")
    public String getTask(@RequestParam String list, @RequestParam String name) {
        List lists = new List();
        lists.setName(list);
        lists.setUser(userRepository.findByName(name));
        listRepository.save(lists);

        return "redirect:/main";
    }

    @PostMapping("/list/del")
    @Transactional
    public String deleteTask(@RequestParam String name) {
        listRepository.deleteByName(name);

        return "redirect:/main";
    }
}
