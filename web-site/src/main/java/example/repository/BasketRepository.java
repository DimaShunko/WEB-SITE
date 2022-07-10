package example.repository;

import example.model.Basket;
import example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    void deleteByTopicNameAndUser(String name, User user);
}
