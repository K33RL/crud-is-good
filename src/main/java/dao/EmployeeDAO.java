package dao;

import connection.ConnectionPool;
import lombok.extern.java.Log;
import pojo.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

@Log
public class EmployeeDAO implements AbstractDAO<Employee> {


    @Override
    public void create(Employee employee) {
        String sql = "INSERT INTO public.employee (employee_name, employee_surname," +
                " employee_patronymic, employee_nsp, employee_email," +
                " employee_birthdate, employee_exp, employee_grade," +
                " employee_languagelvl, employee_skype, employee_feedback_id," +
                " employee_phone_number, employee_hire_date)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = ConnectionPool.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getSurname());
            preparedStatement.setString(3, employee.getPatronymic());
            preparedStatement.setString(4, employee.getSurname() + "" + employee.getName() + "" + employee.getPatronymic());
            preparedStatement.setString(5, employee.getEmail());
            preparedStatement.setDate(6, employee.getBirthDate());
            preparedStatement.setInt(7, employee.getExperience());
            preparedStatement.setString(8, employee.getDeveloperQualificationLevel());
            preparedStatement.setString(9, employee.getEnglishLanguageLevel());
            preparedStatement.setString(10, employee.getSkype());
            preparedStatement.setLong(11, employee.getFeedback().getId());
            preparedStatement.setLong(12, employee.getPhoneNumber());
            preparedStatement.setDate(13, employee.getHiredDate());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            log.log(Level.INFO, e.getMessage());
        }

    }

    @Override
    public Employee getById(long id) {
        Employee employee = new Employee();
        String sql = "SELECT * FROM employee WHERE employee_id = ?";
        try (PreparedStatement preparedStatement = ConnectionPool.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();
            employee.setId(resultSet.getLong("employee_id"));
            employee.setName(resultSet.getString("employee_name"));
            employee.setSurname(resultSet.getString("employee_surname"));
            employee.setPatronymic(resultSet.getString("employee_patronymic"));
            employee.setEmail(resultSet.getString("employee_email"));
            employee.setBirthDate(resultSet.getDate("employee_birthdate"));
            employee.setExperience(resultSet.getInt("employee_exp"));
            employee.setDeveloperQualificationLevel(resultSet.getString("employee_grade"));
            employee.setEnglishLanguageLevel(resultSet.getString("employee_languegelvl"));
            employee.setSkype(resultSet.getString("employee_skype"));
            employee.setFeedback(new FeedbackDAO().getById(resultSet.getLong("employee_feedback_id")));
            employee.setHiredDate(resultSet.getDate("employee_hire_date"));

        } catch (SQLException e) {
            log.log(Level.INFO, e.getMessage());
        }
        return employee;
    }

    @Override
    public Collection getAll() {
        Set<Employee> employees = new HashSet<>();
        String sql = "SELECT * FROM employee";
        try (PreparedStatement preparedStatement = ConnectionPool.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("employee_id"));
                employee.setName(resultSet.getString("employee_name"));
                employee.setSurname(resultSet.getString("employee_surname"));
                employee.setPatronymic(resultSet.getString("employee_patronymic"));
                employee.setEmail(resultSet.getString("employee_email"));
                employee.setBirthDate(resultSet.getDate("employee_birthdate"));
                employee.setExperience(resultSet.getInt("employee_exp"));
                employee.setDeveloperQualificationLevel(resultSet.getString("employee_grade"));
                employee.setEnglishLanguageLevel(resultSet.getString("employee_languegelvl"));
                employee.setSkype(resultSet.getString("employee_skype"));
                employee.setFeedback(new FeedbackDAO().getById(resultSet.getLong("employee_feedback_id")));
                employee.setHiredDate(resultSet.getDate("employee_hire_date"));

                employees.add(employee);
            }
        } catch (SQLException e) {
            log.log(Level.INFO, e.getMessage());
        }

        return employees;
    }

    @Override
    public void update(long id, Employee employee) {
        String sql = "UPDATE employee SET employee_name = ?, employee_surname = ?,employee_patronymic+?," +
                " employee_nsp =?, employee_email=?,employee_birthdate=?," +
                " employee_exp=?, employee_grade=?, employee_languagelvl=?," +
                " employee_skype=?, employee_feedback_id=?,employee_phone_number=?" +
                "WHERE employee_id=?";
        try (PreparedStatement preparedStatement = ConnectionPool.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getSurname());
            preparedStatement.setString(3, employee.getPatronymic());
            preparedStatement.setString(4, employee.getSurname() + "" + employee.getName() + "" + employee.getPatronymic());
            preparedStatement.setString(5, employee.getEmail());
            preparedStatement.setDate(6, employee.getBirthDate());
            preparedStatement.setInt(7, employee.getExperience());
            preparedStatement.setString(8, employee.getDeveloperQualificationLevel());
            preparedStatement.setString(9, employee.getEnglishLanguageLevel());
            preparedStatement.setString(10, employee.getSkype());
            preparedStatement.setLong(11, employee.getFeedback().getId());
            preparedStatement.setLong(12, employee.getPhoneNumber());
            preparedStatement.setLong(13, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.INFO, e.getMessage());
        }
        log.log(Level.FINE, "successful update");
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM employee WHERE employee_id=?";
        try (PreparedStatement preparedStatement = ConnectionPool.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.INFO, e.getMessage());
        }

        log.log(Level.FINE, "successful delete");
    }
}
