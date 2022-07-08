package example.service;

import example.model.Topic;
import example.model.User;
import example.repository.TopicRepository;
import example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TopicRepository topicRepository;

    public Topic getListByNameAndUser(String listName, String userName){
        User user = userRepository.findByName(userName);
        return topicRepository.findByNameAndUser(listName, user);
    }
}
