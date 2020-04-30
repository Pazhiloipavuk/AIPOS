package lab3.dao;

import lab3.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDao extends Dao<User> {
    Optional<User> findByUsername(String username);
}
