package example.repository;

import example.model.List;
import example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepository  extends JpaRepository<List, Long> {
    List findByName(String name);
    void deleteByName(String name);
    List findByNameAndUser(String name, User user);
}
