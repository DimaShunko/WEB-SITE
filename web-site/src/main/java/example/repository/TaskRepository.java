package example.repository;

import example.model.Task;
import example.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findByNameAndTopic(String name, Topic topic);
}
