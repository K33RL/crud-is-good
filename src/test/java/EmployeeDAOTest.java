import dao.EmployeeDAO;
import dao.FeedbackDAO;
import lombok.extern.java.Log;
import org.junit.Test;
import pojo.Employee;
import pojo.Feedback;

import java.sql.Date;
import java.util.Calendar;
import java.util.logging.Level;

@Log
public class EmployeeDAOTest {
    Employee employee = new Employee();
    EmployeeDAO employeeDAO = new EmployeeDAO();
    FeedbackDAO feedbackDAO = new FeedbackDAO();
    Feedback feedback = new Feedback();
    Calendar calendar = Calendar.getInstance();

    {
        calendar.set(2021, Calendar.AUGUST, 10);
        feedback.setId(1);
        feedback.setDescription("some text");
        feedback.setDate(new Date(calendar.getTime().getTime()));
    }


    public void initEmployee(Employee employee) {
        employee.setName("Ivan");
        employee.setSurname("Stepanov");
        employee.setPatronymic("Olegovich");
        employee.setEmail("wello@horld.com");
        employee.setBirthDate(new Date(1996, 01, 03));
        employee.setExperience(1);
        employee.setDeveloperQualificationLevel("s1");
        employee.setEnglishLanguageLevel("b1");
        employee.setSkype("hello world");
        employee.setFeedback(feedbackDAO.getById(1));
        employee.setPhoneNumber(816617171l);
        employee.setHiredDate(new Date(2005,12,2));

        log.log(Level.INFO, "successfully initiation" + employee);


    }

    public void initFeedback(Feedback feedback) {
        calendar.set(2022, Calendar.OCTOBER, 2);
        feedback.setDescription("сборник мыслей о...");
        feedback.setDate(new Date(calendar.getTime().getTime()));

        log.log(Level.INFO, "successfully initiation" + feedback);

    }


    @Test
    public void empDAOTest() {
//        feedback = feedbackDAO.getById(1);
//        initEmployee(employee);
//        System.out.println(employee);
//        employeeDAO.create(employee);
        feedbackDAO.delete(2);
    }
}
