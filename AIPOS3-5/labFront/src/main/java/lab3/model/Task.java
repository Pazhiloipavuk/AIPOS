package lab3.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

@Data
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "name"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Task.class)
public class Task implements Entity {

    private Long id;

    private String name;

    private Description description;
}
