package example.service;

import example.model.User;

import java.util.List;

public interface UserService {
    boolean saveUser(User user);
    User getUser(String username);
    List<User> getUsers();
    boolean deleteUser(Long id);
}
