package example.repository;

import example.model.Topic;
import example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Topic findByNameAndUser(String name, User user);
}
