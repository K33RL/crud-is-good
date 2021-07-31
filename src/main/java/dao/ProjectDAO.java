package dao;

import connection.ConnectionPool;
import lombok.extern.java.Log;
import pojo.Employee;
import pojo.Project;
import pojo.Team;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Level;

@Log
public class ProjectDAO implements AbstractDAO<Project> {
    @Override
    public void create(Project project) {
        String sql = "INSERT INTO project(project_name, project_customer, project_duration, " +
                "project_methodology, project_manager, project_team_id) " +
                " VALUES(?,?,?,?,? , ?)";
        try (PreparedStatement preparedStatement = ConnectionPool.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, project.getProjectName());
            preparedStatement.setString(2, project.getCustomerName());
            preparedStatement.setInt(3, project.getDuration());
            preparedStatement.setString(4, project.getMethodology());
            preparedStatement.setLong(5, project.getProjectManager().getId());
            preparedStatement.setLong(6, project.getTeam().getId());
        } catch (SQLException e) {
            log.log(Level.INFO, e.getMessage());
        }
    }

    @Override
    public Project getById(long id) {
        Project project = new Project();
        String sql = "SELECT * project WHERE project_id=?";
        try (PreparedStatement preparedStatement = ConnectionPool.getConnection().prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            project.setId(resultSet.getLong("project_id"));
            project.setProjectName(resultSet.getString("project_name"));
            project.setCustomerName(resultSet.getString("project_customer"));
            project.setDuration(resultSet.getInt("project_duration"));
            project.setMethodology(resultSet.getString("project_methodology"));
            project.setProjectManager(new EmployeeDAO().getById(resultSet.getLong("project_manager")));
            project.setTeam(new TeamDAO().getById(resultSet.getLong("project_team_id")));


        } catch (SQLException e) {
            log.log(Level.INFO, e.getMessage());
        }
        return project;
    }

    @Override
    public Collection<Project> getAll() {
        return null;
    }

    @Override
    public void update(long id, Project project) {

    }

    @Override
    public void delete(long id) {

    }
}
