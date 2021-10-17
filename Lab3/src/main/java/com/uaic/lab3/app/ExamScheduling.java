package com.uaic.lab3.app;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

import com.uaic.lab3.dao.ExamController;
import com.uaic.lab3.dao.StudentController;
import com.uaic.lab3.db.Database;

public class ExamScheduling {
    public static Connection connection;
    private static final ExamController examController = new ExamController();
    private static final StudentController studentController = new StudentController();

    public ExamScheduling() throws SQLException {
    }

    public static void main(String[] args) {
        try {
            connection = Database.getInstance().getConnection();
            connection.setAutoCommit(false);
            //insertMockData();
            connection.commit();

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

    }

    public static void insertMockData(){
        insertMockExams();
        insertMockStudents();
    }

    public static void insertMockExams() {
        examController.create("Java Technologies", Date.valueOf("2021-10-18"), 60);
        examController.create("ASET", Date.valueOf("2021-10-18"), 30);
        examController.create("Blockchain", Date.valueOf("2021-10-19"), 90);
    }

    public static void insertMockStudents(){
        studentController.create("Diana Crainic");
        studentController.create("Maria Popescu");
        studentController.create("Andrei Irimescu");
    }

    public static void createTables() {
        createExamsTable();
        createStudentsTable();
    }

    public static void createExamsTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "create table exams(" +
                    "    id integer not null generated always as identity," +
                    "    name varchar(100) not null," +
                    "    starting_time varchar(100)," +
                    "    duration integer," +
                    "    primary key (id)" +
                    ");";
            statement.execute(query);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void createStudentsTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "create table students(" +
                    "    id integer not null generated always as identity," +
                    "    name varchar(100) not null," +
                    "    primary key (id)" +
                    ");";
            statement.execute(query);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}

