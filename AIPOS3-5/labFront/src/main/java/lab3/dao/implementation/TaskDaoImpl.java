package lab3.dao.implementation;

import lab3.dao.TaskDao;
import lab3.model.Task;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Repository
public class TaskDaoImpl extends AbstractDao<Task> implements TaskDao {

    private static final String ENTITY_URL = "/tasks";
    private static final String FREE_TASKS_URL = "/free";

    public TaskDaoImpl(RestTemplate restTemplate) {
        super(Task.class, Task[].class, restTemplate, ENTITY_URL);
    }

    @Override
    public Collection<Task> findAllByDescriptionIsNull() {
        String url = String.join(Strings.EMPTY, getUrl(), FREE_TASKS_URL);
        return Optional.ofNullable(getRestTemplate().getForEntity(url, getTargetEntities()).getBody())
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }
}