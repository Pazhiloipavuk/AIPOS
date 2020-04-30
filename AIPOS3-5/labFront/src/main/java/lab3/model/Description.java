package lab3.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.util.Collection;

@Data
@ToString(of = {"id", "title", "content"})
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Description.class)
public class Description implements Entity {

    private Long id;

    private String title;

    private String content;

    private Task task;

    private User author;

    private Collection<Comment> comments;
}
