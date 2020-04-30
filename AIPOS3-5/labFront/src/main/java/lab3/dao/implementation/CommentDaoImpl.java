package lab3.dao.implementation;

import lab3.dao.CommentDao;
import lab3.model.Comment;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class CommentDaoImpl extends AbstractDao<Comment> implements CommentDao {

    private static final String ENTITY_URL = "/comments";

    public CommentDaoImpl(RestTemplate restTemplate) {
        super(Comment.class, Comment[].class, restTemplate, ENTITY_URL);
    }
}
