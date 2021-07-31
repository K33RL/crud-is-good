package pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Employee {
    private long id;
    private String name;
    private String surname;
    private String patronymic;
    private String email;
    private Long phoneNumber;
    private Date birthDate;
    private int experience;
    private Date hiredDate;
    private Project project;
    private String developerQualificationLevel;
    private String englishLanguageLevel;
    private String skype;
    private Feedback feedback;
}
