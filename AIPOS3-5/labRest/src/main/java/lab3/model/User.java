package lab3.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Data
@ToString(of = {"id", "username"})
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_model")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = User.class)
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    @OneToMany(mappedBy = "author")
    private Collection<Description> descriptions;

    @OneToMany(mappedBy = "author")
    private Collection<Comment> comments;
}
