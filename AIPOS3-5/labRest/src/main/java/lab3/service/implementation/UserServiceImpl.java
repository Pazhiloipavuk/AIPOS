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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

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
    public User save(User user) {
        return userDao.save(user);
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
    public void delete(User user) {
        user.getComments().forEach(commentService::delete);
        user.getDescriptions().forEach(descriptionService::delete);
        userDao.delete(user);
    }

    @Override
    public void deleteById(Long id) {
        delete(findById(id).orElseThrow(ExceptionInLab::new));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}