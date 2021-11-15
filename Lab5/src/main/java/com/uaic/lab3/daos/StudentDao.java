package com.uaic.lab3.daos;

import com.uaic.lab3.dtos.ExamDto;
import com.uaic.lab3.dtos.StudentDto;
import com.uaic.lab3.entities.Student;

import javax.naming.NamingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDao extends Dao {
    public StudentDao() throws NamingException {
        super();
    }

    public List<Student> getAll() throws SQLException {
        List<Student> students = new ArrayList<>();
        String query = "SELECT id, name, exam_ids FROM students;";
        try (Statement statement = getConnection().createStatement()) {
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
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(command)) {
            preparedStatement.setString(1, studentDto.getName());
            preparedStatement.setString(2, studentDto.getAssignedExams());
            preparedStatement.execute();
        }
    }

    public Student getById(Integer id) throws SQLException {
        Student student = null;
        String query = "SELECT name, exam_ids FROM students WHERE id = ?;";
        try (PreparedStatement preparedStatement =
                     getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String assignedExams = resultSet.getString("exam_ids");
                student = new Student(id, name, assignedExams);
            }
        }
        return student;
    }

    public void update(Student student) throws SQLException {
        String command = "UPDATE students SET name = ?, exam_ids = ? WHERE id = ?;";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(command)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getAssignedExams());
            preparedStatement.setInt(3, student.getId());
            preparedStatement.execute();
        }
    }
}
