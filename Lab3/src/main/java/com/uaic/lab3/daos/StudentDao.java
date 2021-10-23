package com.uaic.lab3.daos;

import com.uaic.lab3.entities.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDao extends Dao {
    private static final String SELECT_ALL_STUDENTS_QUERY = "SELECT id, name FROM students;";
    private final ExamDao examDao;

    public StudentDao() {
        examDao = new ExamDao();
        try {
            connection = getConnection();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public List<Student> getAll() throws SQLException {
        List<Student> students = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_STUDENTS_QUERY);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Student student = new Student(id, name, null);
                students.add(student);
            }
        }
        return students;
    }
}
