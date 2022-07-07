package example.service;

import example.model.List;
import example.model.User;
import example.repository.ListRepository;
import example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ListRepository listRepository;

    public List getListByNameAndUser(String listName, String userName){
        User user = userRepository.findByName(userName);
        return listRepository.findByNameAndUser(listName, user);
    }
}
