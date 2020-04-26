package lab3.controller;

import lab3.service.MainService;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@NoArgsConstructor
abstract public class AbstractController<E> {

    private static final String ID_URL_PARAMETER = "/{id}";

    private MainService<E> mainService;

    public AbstractController(MainService<E> systemService) {
        this.mainService = systemService;
    }

    @PutMapping
    public E save(@RequestBody E entity) {
        return mainService.save(entity);
    }

    @GetMapping(ID_URL_PARAMETER)
    public E findById(@PathVariable Long id) {
        return mainService.findById(id).orElse(null);
    }

    @GetMapping
    public List<E> findAll() {
        return mainService.findAll();
    }

    @DeleteMapping(ID_URL_PARAMETER)
    public void deleteById(@PathVariable Long id) {
        mainService.deleteById(id);
    }
}
