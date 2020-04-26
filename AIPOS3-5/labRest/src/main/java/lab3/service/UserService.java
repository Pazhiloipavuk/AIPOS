package lab3.service;

import lab3.model.User;

import java.util.Optional;

public interface UserService extends MainService<User> {
    Optional<User> findByUsername(String username);
}
