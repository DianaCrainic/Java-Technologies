package com.uaic.lab3.daos;

import com.uaic.lab3.entities.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDao extends Dao {
    public StudentDao() {
        try {
            connection = getConnection();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public List<Student> getAll() throws SQLException {
        List<Student> students = new ArrayList<>();
        String query = "SELECT id, name FROM students;";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
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
