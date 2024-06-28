package com.sidorov.test.services;

import com.sidorov.test.models.Book;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class LibraryTest {
    private Library library;

    @Before
    public void setUp() {
        library = new Library();
    }

    @Test
    public void testAddBook() {
        Book book = new Book("Title1", "Author1", 2023, "ISBN001");
        library.addBook(book);
        List<Book> books = library.findBooksByTitle("Title1");
        assertEquals(1, books.size());
        assertEquals(book, books.get(0));
    }

    @Test
    public void testRemoveBook() {
        Book book = new Book("Title1", "Author1", 2023, "ISBN001");
        library.addBook(book);
        library.removeBook("ISBN001");
        List<Book> books = library.findBooksByTitle("Title1");
        assertEquals(0, books.size());
    }

    @Test
    public void testFindBooksByTitle() {
        Book book1 = new Book("Title1", "Author1", 2023, "ISBN001");
        Book book2 = new Book("Title2", "Author2", 2024, "ISBN002");
        library.addBook(book1);
        library.addBook(book2);
        List<Book> books = library.findBooksByTitle("Title1");
        assertEquals(1, books.size());
        assertEquals(book1, books.get(0));
    }

    @Test
    public void testFindBooksByAuthor() {
        Book book1 = new Book("Title1", "Author1", 2023, "ISBN001");
        Book book2 = new Book("Title2", "Author2", 2024, "ISBN002");
        library.addBook(book1);
        library.addBook(book2);
        List<Book> books = library.findBooksByAuthor("Author2");
        assertEquals(1, books.size());
        assertEquals(book2, books.get(0));
    }

    @Test
    public void testFindBooksByYear() {
        Book book1 = new Book("Title1", "Author1", 2023, "ISBN001");
        Book book2 = new Book("Title2", "Author2", 2024, "ISBN002");
        library.addBook(book1);
        library.addBook(book2);
        List<Book> books = library.findBooksByYear(2023);
        assertEquals(1, books.size());
        assertEquals(book1, books.get(0));
    }
}
