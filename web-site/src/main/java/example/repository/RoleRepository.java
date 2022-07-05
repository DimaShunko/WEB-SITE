package example.repository;

import example.model.List;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RoleRepository extends JpaRepository<List, Long> {
    List findByName(String name);
}
