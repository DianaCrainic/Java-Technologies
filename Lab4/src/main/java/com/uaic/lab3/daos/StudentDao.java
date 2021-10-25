package com.uaic.lab3.daos;

import com.uaic.lab3.dtos.ExamDto;
import com.uaic.lab3.dtos.StudentDto;
import com.uaic.lab3.entities.Student;

import java.sql.PreparedStatement;
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
        String query = "SELECT id, name, exam_ids FROM students;";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String examIds = resultSet.getString("exam_ids");
                Student student = new Student(id, name, examIds);
                students.add(student);
            }
        }
        return students;
    }

    public void create(StudentDto studentDto) throws SQLException {
        String command = "INSERT INTO students(name, exam_ids) VALUES (?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(command)) {
            preparedStatement.setString(1, studentDto.getName());
            preparedStatement.setString(2, studentDto.getAssignedExams());
            preparedStatement.execute();
        }
    }
}
