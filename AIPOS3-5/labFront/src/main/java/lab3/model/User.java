package lab3.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collection;

@Data
@ToString(of = {"id", "username"})
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = User.class)
public class User implements Entity {

    private Long id;

    private String username;

    private Collection<Description> descriptions;

    private Collection<Comment> comments;
}
