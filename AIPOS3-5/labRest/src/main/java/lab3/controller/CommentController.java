package lab3.controller;

import lab3.model.Comment;
import lab3.service.MainService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static lab3.controller.ControllerConstants.COMMENTS_URL;

@RestController
@NoArgsConstructor
@RequestMapping(COMMENTS_URL)
public class CommentController extends AbstractController<Comment> {

    @Autowired
    public CommentController(MainService<Comment> mainService) {
        super(mainService);
    }
}
