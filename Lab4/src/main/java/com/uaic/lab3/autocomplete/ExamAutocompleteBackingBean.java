package com.uaic.lab3.autocomplete;

import com.uaic.lab3.daos.ExamDao;
import com.uaic.lab3.entities.Exam;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@SessionScoped
public class ExamAutocompleteBackingBean extends AutocompleteBackingBean<Exam, Integer> {
    private final ExamDao examDao;

    public ExamAutocompleteBackingBean() throws SQLException {
        examDao = new ExamDao();
    }

    public List<String> completeText(String query) throws SQLException {
        List<String> listOfExams = new ArrayList<>();
        List<Exam> exams = examDao.getAll();
        for (Exam exam : exams) {
            listOfExams.add(exam.getName());
        }
        return listOfExams.stream().filter(t -> t.toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
    }
}
