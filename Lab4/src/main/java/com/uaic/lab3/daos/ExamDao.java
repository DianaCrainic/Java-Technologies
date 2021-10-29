package com.uaic.lab3.daos;

import com.uaic.lab3.dtos.ExamDto;
import com.uaic.lab3.entities.Exam;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExamDao extends Dao {
    public ExamDao() {
        try {
            connection = getConnection();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public List<Exam> getAll() throws SQLException {
        List<Exam> exams = new ArrayList<>();
        String query = "SELECT id, name, starting_time, duration FROM exams;";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Timestamp startingTime = resultSet.getTimestamp("starting_time");
                Integer duration = resultSet.getInt("duration");
                Exam exam = new Exam(id, name, startingTime, duration);
                exams.add(exam);
            }
        }
        return exams;
    }

    public void create(ExamDto exam) throws SQLException {
        String command = "INSERT INTO exams(name, starting_time, duration) VALUES (?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(command)) {
            preparedStatement.setString(1, exam.getName());
            preparedStatement.setTimestamp(2, exam.getStartingTime());
            preparedStatement.setInt(3, exam.getDuration());
            preparedStatement.execute();
        }
    }

    public Exam getById(Integer id) throws SQLException {
        Exam exam = null;
        String query = "SELECT name, starting_time, duration FROM exams WHERE id = ?;";
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                Timestamp startingTime = resultSet.getTimestamp("starting_time");
                Integer duration = resultSet.getInt("duration");
                exam = new Exam(id, name, startingTime, duration);
            }
        }
        return exam;
    }

    public void update(Exam exam) throws SQLException {
        String command = "UPDATE exams SET starting_time = ?, duration = ? WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(command)) {
            Timestamp startingTime = new Timestamp(exam.getStartingTime().getTime());
            preparedStatement.setTimestamp(1, startingTime);
            preparedStatement.setInt(2, exam.getDuration());
            preparedStatement.setInt(3, exam.getId());
            preparedStatement.execute();
        }
    }
}
