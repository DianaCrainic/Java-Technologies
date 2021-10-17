package com.uaic.lab3.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalTime;

public class ExamController extends Controller {

    public ExamController() {
        try {
            connection = getConnection();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void create(String name, Date startingTime, int duration) {
        try {
            String query = "insert into exams(name, starting_time, duration) values(?, ?, ?);";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setDate(2, startingTime);
            preparedStatement.setInt(3, duration);
            preparedStatement.execute();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
