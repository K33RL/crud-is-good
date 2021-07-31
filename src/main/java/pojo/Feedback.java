package pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Feedback {
    private long id;
    private String description;
    private Date date;
}
