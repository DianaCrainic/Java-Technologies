package com.uaic.Lab9.algorithm;

import com.uaic.Lab9.entities.Book;
import com.uaic.Lab9.entities.Library;

import java.util.ArrayList;
import java.util.List;

/**
 * BestBooksForLibraryAlgorithm:
 * is an algorithm which has as input an id of a library (which has a specified budget)
 * and a list o books.
 * The algorithm outputs a list of books (each book having a price and a review - min. 1, max 5)
 * from this list of books
 * which this library can afford and are the most profitable
 * (the ratio between price and review is the minimum).
 */
public class BestBooksForLibraryAlgorithm implements Algorithm {
    /**
     * returns a list of the most profitable books
     */
    @Override
    public List<Book> solve(Library library, List<Book> books) {
        int numberOfBooks = books.size();
        int libraryBudget = library.getBudget();

        if (numberOfBooks <= 0 || libraryBudget <= 0) {
            return null;
        }

        double[][] matrix = new double[numberOfBooks][2];

        numberOfBooks = createMatrix(matrix, numberOfBooks, libraryBudget, books);

        sortMatrixOnColumn(matrix, numberOfBooks); //sort on ratio

        return createListOfBooks(numberOfBooks, libraryBudget, matrix, books);
    }

    /**
     * creates a matrix saving the id of the book and the ratio between the price and review
     */
    private int createMatrix(double[][] m, int numberOfBooks, int libraryBudget, List<Book> books) {
        int numberOfRows = 0;
        int i = 0;
        while (i < numberOfBooks) {
            if (books.get(i).getPrice() <= libraryBudget) {
                int bookId = books.get(i).getId();
                double ratio = books.get(i).getPrice() / (double) books.get(i).getReview();

                m[numberOfRows][0] = bookId;
                m[numberOfRows][1] = ratio;
                numberOfRows++;
            }
            i++;
        }
        return numberOfRows;
    }

    /**
     * sorting the matrix ascending on columns (ratio)
     */
    private void sortMatrixOnColumn(double[][] matrix, int numberOfRows) {
        for (int i = 0; i < numberOfRows - 1; i++) {
            for (int j = i + 1; j < numberOfRows; j++) {
                if (matrix[i][1] > matrix[j][1]) {
                    double[] aux = matrix[i];
                    matrix[i] = matrix[j];
                    matrix[j] = aux;
                }
            }
        }
    }

    /**
     * adds the books to the list which are the most profitable
     */
    private List<Book> createListOfBooks(int numberOfBooks, int libraryBudget, double[][] m, List<Book> books) {
        List<Book> booksForThisLibrary = new ArrayList<>();
        for (int i = 0; i < numberOfBooks && libraryBudget >= 0; i++) {
            Book book = getBookById((int) m[i][0], books);
            assert book != null;
            if (book.getPrice() <= libraryBudget) {
                booksForThisLibrary.add(book);
                libraryBudget = libraryBudget - book.getPrice();
            }
        }
        return booksForThisLibrary;
    }

    /**
     * outputs the book from the list with the specified id
     */
    private Book getBookById(Integer bookId, List<Book> books) {
        for (Book book : books) {
            if (bookId.equals(book.getId())) {
                return book;
            }
        }
        return null;
    }
}
