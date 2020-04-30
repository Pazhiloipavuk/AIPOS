package lab3.dao.implementation;

import lab3.dao.Dao;
import lab3.model.Entity;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Getter
public abstract class AbstractDao<E extends Entity> implements Dao<E> {

    protected static final String URL_DELIMITER = "/";

    private Class<E> targetEntity;

    private Class<E[]> targetEntities;

    private RestTemplate restTemplate;

    @Value("${rest.url}")
    private String restApiUrl;

    private String entityUrl;

    private String url;

    public AbstractDao(Class<E> targetEntity, Class<E[]> targetEntities, RestTemplate restTemplate, String url) {
        this.targetEntity = targetEntity;
        this.targetEntities = targetEntities;
        this.restTemplate = restTemplate;
        this.entityUrl = url;
    }

    @PostConstruct
    private void initialize() {
        url = String.join(Strings.EMPTY, restApiUrl, entityUrl);
    }

    @Override
    public E save(E entity) {
        return restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(entity), targetEntity).getBody();
    }

    @Override
    public Optional<E> findById(Long id) {
        return Optional.ofNullable(restTemplate.getForEntity(resolveUrl(id), targetEntity).getBody());
    }

    @Override
    public Collection<E> findAll() {
        return Optional.ofNullable(restTemplate.getForEntity(url, targetEntities).getBody())
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    @Override
    public void delete(E entity) {
        restTemplate.delete(resolveUrl(entity.getId()));
    }

    private String resolveUrl(Long id) {
        return String.join(URL_DELIMITER, url, id.toString());
    }
}

