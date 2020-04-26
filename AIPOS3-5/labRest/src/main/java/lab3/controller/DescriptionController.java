package lab3.controller;

import lab3.model.Description;
import lab3.service.MainService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static lab3.controller.ControllerConstants.DESCRIPTIONS_URL;

@RestController
@NoArgsConstructor
@RequestMapping(DESCRIPTIONS_URL)
public class DescriptionController extends AbstractController<Description> {

    @Autowired
    public DescriptionController(MainService<Description> mainService) {
        super(mainService);
    }
}
