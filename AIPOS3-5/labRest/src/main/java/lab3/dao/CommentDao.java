package lab3.dao;

import lab3.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentDao extends CrudRepository<Comment, Long> {
}
