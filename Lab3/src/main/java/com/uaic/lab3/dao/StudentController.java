package com.uaic.lab3.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentController extends Controller {

    public StudentController() {
        try {
            connection = getConnection();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void create(String name) {
        try {
            String query = "insert into students(name) values(?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }


}
