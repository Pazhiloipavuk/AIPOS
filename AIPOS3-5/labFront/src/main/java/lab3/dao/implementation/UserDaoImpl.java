package lab3.dao.implementation;

import lab3.dao.UserDao;
import lab3.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    private static final String ENTITY_URL = "/users";
    private static final String USERNAME_URL = "username";

    public UserDaoImpl(RestTemplate restTemplate) {
        super(User.class, User[].class, restTemplate, ENTITY_URL);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(getRestTemplate().getForEntity(resolveUrl(username), getTargetEntity()).getBody());
    }

    private String resolveUrl(String username) {
        return String.join(URL_DELIMITER, getUrl(), USERNAME_URL, username);
    }
}
