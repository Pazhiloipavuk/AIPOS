package lab3.dao.implementation;

import lab3.dao.DescriptionDao;
import lab3.model.Description;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class DescriptionDaoImpl extends AbstractDao<Description> implements DescriptionDao {

    private static final String ENTITY_URL = "/descriptions";

    public DescriptionDaoImpl(RestTemplate restTemplate) {
        super(Description.class, Description[].class, restTemplate, ENTITY_URL);
    }
}

