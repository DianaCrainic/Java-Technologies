package com.uaic.Lab9.algorithm;

import com.uaic.Lab9.entities.Book;
import com.uaic.Lab9.entities.Library;

import java.util.List;

public interface Algorithm {
    List<Book> solve(Library library, List<Book> books);
}
