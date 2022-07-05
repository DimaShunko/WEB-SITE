package example.repository;

import example.model.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepository  extends JpaRepository<List, Long> {
    void deleteByName(String name);
}
