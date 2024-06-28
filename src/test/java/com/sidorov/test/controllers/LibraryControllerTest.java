package com.sidorov.test.controllers;

import com.sidorov.test.controller.LibraryController;
import com.sidorov.test.models.Book;
import com.sidorov.test.services.Library;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class LibraryControllerTest {
    private LibraryController libraryController;
    private Library library;
    private InputStream originalIn;

    @Before
    public void setUp() {
        library = mock(Library.class);
        originalIn = System.in;
    }

    @Test
    public void testRunLibraryManagement_AddBook() {

        String input = "1\nTitle1\nAuthor1\n2023\nISBN001\n7\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in); // Устанавливаем новый System.in

        libraryController = new LibraryController(library);
        libraryController.runLibraryManagement();


        verify(library, times(1)).addBook(any(Book.class));

        System.setIn(originalIn);
    }

    @Test
    public void testRunLibraryManagement_RemoveBook() {

        String input = "2\nISBN001\n7\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        libraryController = new LibraryController(library);
        libraryController.runLibraryManagement();


        verify(library, times(1)).removeBook(anyString());

        System.setIn(originalIn);
    }

    @Test
    public void testRunLibraryManagement_FindBookByTitle() {
        List<Book> mockBooks = new ArrayList<>();
        mockBooks.add(new Book("Title1", "Author1", 2023, "ISBN001"));
        when(library.findBooksByTitle(anyString())).thenReturn(mockBooks);


        String input = "3\nTitle1\n7\n"; // Найти книгу по названию, затем выход
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in); // Устанавливаем новый System.in

        libraryController = new LibraryController(library);
        libraryController.runLibraryManagement();

        verify(library, times(1)).findBooksByTitle(anyString());

        System.setIn(originalIn);
    }

    @Test
    public void testRunLibraryManagement_FindBookByAuthor() {

        List<Book> mockBooks = new ArrayList<>();
        mockBooks.add(new Book("Title1", "Author1", 2023, "ISBN001"));
        when(library.findBooksByAuthor(anyString())).thenReturn(mockBooks);


        String input = "4\nAuthor1\n7\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        libraryController = new LibraryController(library);
        libraryController.runLibraryManagement();


        verify(library, times(1)).findBooksByAuthor(anyString());

        System.setIn(originalIn);
    }

    @Test
    public void testRunLibraryManagement_FindBookByYear() {
        // Подготовка данных для поиска книг по году издания
        List<Book> mockBooks = new ArrayList<>();
        mockBooks.add(new Book("Title1", "Author1", 2023, "ISBN001"));
        when(library.findBooksByYear(anyInt())).thenReturn(mockBooks);

        String input = "5\n2023\n7\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        libraryController = new LibraryController(library);
        libraryController.runLibraryManagement();

        verify(library, times(1)).findBooksByYear(anyInt());

        System.setIn(originalIn);
    }
}
