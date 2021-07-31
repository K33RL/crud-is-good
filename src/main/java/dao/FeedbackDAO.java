package dao;

import connection.ConnectionPool;
import lombok.extern.java.Log;
import pojo.Feedback;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;

@Log
public class FeedbackDAO implements AbstractDAO<Feedback> {
    @Override
    public void create(Feedback feedback) {
        String sql = "INSERT INTO feedback (feedback_text, feedback_date) VALUES (?,?)";
        try (PreparedStatement preparedStatement = ConnectionPool.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, feedback.getDescription());
            preparedStatement.setDate(2, feedback.getDate());

            preparedStatement.executeUpdate();
            log.log(Level.FINE, "successfully added object: " + feedback);
        } catch (SQLException e) {
            log.log(Level.INFO, e.getMessage());
        }
    }

    @Override
    public Feedback getById(long id) {
        Feedback feedback = new Feedback();
        String sql = "SELECT * FROM feedback WHERE feedback_id=?";
        try (PreparedStatement preparedStatement = ConnectionPool.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            feedback.setId(resultSet.getLong("feedback_id"));
            feedback.setDescription(resultSet.getString("feedback_text"));
            feedback.setDate(resultSet.getDate("feedback_date"));
        } catch (SQLException e) {
            log.log(Level.INFO, e.getMessage());
        }

        return feedback;
    }

    @Override
    public Collection<Feedback> getAll() {
        List<Feedback> feedbacks = new ArrayList<>();
        String sql = "SELECT * FROM feedback";
        try (PreparedStatement preparedStatement = ConnectionPool.getConnection().prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Feedback feedback = new Feedback();
                feedback.setId(resultSet.getLong("feedback_id"));
                feedback.setDescription(resultSet.getString("feedback_text"));
                feedback.setDate(resultSet.getDate("feedback_date"));

                feedbacks.add(feedback);
            }

        } catch (SQLException | NullPointerException e) {
            log.log(Level.INFO, e.getMessage());
        }
        return feedbacks;
    }

    @Override
    public void update(long id, Feedback feedback) {
        String sql = "UPDATE feedback SET feedback_text=? WHERE id=?";
        try (PreparedStatement preparedStatement = ConnectionPool.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, feedback.getDescription());
            preparedStatement.setLong(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.INFO, e.getMessage());
        }
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM feedback WHERE feedback_id=?";
        try (PreparedStatement preparedStatement = ConnectionPool.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.INFO, e.getMessage());
        }
    }
}
