package lab3.service.implementation;

import lab3.dao.DescriptionDao;
import lab3.exception.ExceptionInLab;
import lab3.model.Description;
import lab3.service.CommentService;
import lab3.service.DescriptionService;
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
public class DescriptionServiceImpl implements DescriptionService {

    private DescriptionDao descriptionDao;

    private CommentService commentService;

    @Autowired
    public DescriptionServiceImpl(DescriptionDao descriptionDao, CommentService commentService) {
        this.descriptionDao = descriptionDao;
        this.commentService = commentService;
    }

    @Override
    public Description save(Description entity) {
        return descriptionDao.save(entity);
    }

    @Override
    public Optional<Description> findById(Long id) {
        return descriptionDao.findById(id);
    }

    @Override
    public List<Description> findAll() {
        return IterableUtils.toList(descriptionDao.findAll());
    }

    @Override
    public void delete(Description entity) {
        entity.getComments().forEach(commentService::delete);
        descriptionDao.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        delete(findById(id).orElseThrow(ExceptionInLab::new));
    }
}
