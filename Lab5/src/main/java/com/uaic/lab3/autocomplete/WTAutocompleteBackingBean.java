package com.uaic.lab3.autocomplete;

import com.uaic.lab3.daos.WrittenTestDao;
import com.uaic.lab3.entities.WrittenTest;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@SessionScoped
public class WTAutocompleteBackingBean extends AutocompleteBackingBean {
    private WrittenTestDao writtenTestDao;

    @PostConstruct
    public void init() {
        writtenTestDao = new WrittenTestDao();
    }

    public List<String> completeText(String query) {
        String queryLowerCase = query.toLowerCase();
        List<String> examList = new ArrayList<>();
        List<WrittenTest> exams = writtenTestDao.getAll();
        for (WrittenTest exam : exams) {
            examList.add(exam.getName());
        }

        return examList.stream().filter(t -> t.toLowerCase().startsWith(queryLowerCase)).collect(Collectors.toList());
    }
}
