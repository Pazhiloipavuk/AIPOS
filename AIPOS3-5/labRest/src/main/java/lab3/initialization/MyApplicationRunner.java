package lab3.initialization;

import lab3.dao.CommentDao;
import lab3.dao.DescriptionDao;
import lab3.dao.TaskDao;
import lab3.dao.UserDao;
import lab3.model.Comment;
import lab3.model.Description;
import lab3.model.Task;
import lab3.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class MyApplicationRunner implements ApplicationRunner {

    private DescriptionDao descriptionDao;

    private UserDao userDao;

    private CommentDao commentDao;

    private TaskDao taskDao;

    @Override
    public void run(ApplicationArguments args) {
        User userBob = createUser("UserBob");
        User userPavel = createUser("UserPavel");
        User userArty = createUser("UserArty");

        Description descriptionBread = createTaskAndDescription(userBob, "Meal", "Buy bread");
        Description descriptionBeer = createTaskAndDescription(userPavel, "Water", "Buy beer");
        Description descriptionMedicine = createTaskAndDescription(userArty, "Medicine", "Buy medicine");

        createComment(userBob, descriptionBread, "Today");
        createComment(userPavel, descriptionBeer, "Weekends");
        createComment(userArty, descriptionMedicine, "NOW!!!");
    }

    private User createUser(String username) {
        var user = User.builder()
                .username(username)
                .build();
        return userDao.save(user);
    }

    private Description createTaskAndDescription(User author, String title, String content) {
        Task task = Task.builder()
                .name(title)
                .build();
        task = taskDao.save(task);
        Description description = Description.builder()
                .title(title)
                .content(content)
                .author(author)
                .task(task)
                .build();
        description = descriptionDao.save(description);
        author.setDescriptions(Collections.singleton(description));
        userDao.save(author);
        task.setDescription(description);
        taskDao.save(task);
        return description;
    }

    private void createComment(User author, Description description, String content) {
        Comment comment = Comment.builder()
                .content(content)
                .description(description)
                .author(author)
                .build();
        commentDao.save(comment);
        author.setComments(Collections.singletonList(comment));
        userDao.save(author);
        description.setComments(Collections.singletonList(comment));
        descriptionDao.save(description);
    }

    @Autowired
    public void setDescriptionDao(DescriptionDao descriptionDao) {
        this.descriptionDao = descriptionDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Autowired
    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }
}
