package com.uaic.lab3.daos;

import com.uaic.lab3.entities.Exam;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExamDao extends Dao {
    private static final String SELECT_EXAM_ID_BY_STUDENT_ID = "SELECT exam_id FROM students_exams WHERE student_id = ?";

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
}
