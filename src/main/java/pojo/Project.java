package pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Project {
    private Long id;
    private String projectName;
    private String customerName;
    private int duration;
    private String methodology;
    private Employee projectManager;
    private Team team;
}
