package lab3.service.implementation;

import lab3.dao.UserDao;
import lab3.exception.ExceptionInLab;
import lab3.model.User;
import lab3.service.CommentService;
import lab3.service.DescriptionService;
import lab3.service.UserService;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private static final String LOGIN_KEY = "name";

    private UserDao userDao;

    private DescriptionService descriptionService;

    private CommentService commentService;

    @Autowired
    public UserServiceImpl(UserDao userDao, DescriptionService descriptionService, CommentService commentService) {
        this.userDao = userDao;
        this.descriptionService = descriptionService;
        this.commentService = commentService;
    }

    @Override
    @Cacheable(value = "users", key = "#user.username")
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public User authorize(OAuth2AuthenticatedPrincipal principal) {
        String username = getUsername(principal);
        return findByUsername(username)
                .orElseGet(() -> save(User.builder().username(username).build()));
    }

    private String getUsername(OAuth2AuthenticatedPrincipal principal) {
        return principal.getAttribute(LOGIN_KEY);
    }

    @Override
    @Cacheable(value = "users")
    public Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public Optional<User> getCurrentUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .filter(this::isAuthorized)
                .map(principal -> (OAuth2AuthenticatedPrincipal) principal)
                .map(this::authorize);
    }

    private boolean isAuthorized(Object principal) {
        return principal instanceof OAuth2AuthenticatedPrincipal;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findAll() {
        return IterableUtils.toList(userDao.findAll());
    }

    @Override
    @CacheEvict(value = "users", key = "#user.id")
    public void delete(User user) {
        user.getComments().forEach(commentService::delete);
        user.getDescriptions().forEach(descriptionService::delete);
        userDao.delete(user);
    }

    @Override
    public void deleteById(Long id) {
        delete(findById(id).orElseThrow(ExceptionInLab::new));
    }
}