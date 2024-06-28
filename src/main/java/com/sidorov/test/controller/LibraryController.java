package com.sidorov.test.controller;

import com.sidorov.test.models.Book;
import com.sidorov.test.services.Library;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Scanner;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class LibraryController {
    private final Library library;
    private final Scanner scanner;

    public LibraryController() {
        this.library = new Library();
        this.scanner = new Scanner(System.in);
    }

    public LibraryController(Library library) {
        this.library = library;
        this.scanner = new Scanner(System.in);
    }

    public void runLibraryManagement() {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    findBookByTitle();
                    break;
                case 4:
                    findBookByAuthor();
                    break;
                case 5:
                    findBookByYear();
                    break;
                case 6:
                    displayAllBooks();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1. Добавить книгу");
        System.out.println("2. Удалить книгу");
        System.out.println("3. Найти книгу по названию");
        System.out.println("4. Найти книгу по автору");
        System.out.println("5. Найти книгу по году издания");
        System.out.println("6. Показать все книги");
        System.out.println("7. Выход");
    }

    private int getUserChoice() {
        System.out.print("Введите номер действия: ");
        return scanner.nextInt();
    }

    private void addBook() {
        scanner.nextLine(); // Считать остаток новой строки

        System.out.println("Введите название книги:");
        String title = scanner.nextLine();

        System.out.println("Введите автора книги:");
        String author = scanner.nextLine();

        System.out.println("Введите год издания книги:");
        int year = scanner.nextInt();
        scanner.nextLine(); // Считать остаток новой строки

        System.out.println("Введите ISBN книги:");
        String isbn = scanner.nextLine();

        Book book = new Book(title, author, year, isbn);
        library.addBook(book);
        System.out.println("Книга успешно добавлена в библиотеку.");
    }

    private void removeBook() {
        scanner.nextLine(); // Считать остаток новой строки

        System.out.println("Введите ISBN книги для удаления:");
        String isbn = scanner.nextLine();
        library.removeBook(isbn);
        System.out.println("Книга успешно удалена из библиотеки.");
    }

    private void findBookByTitle() {
        scanner.nextLine(); // Считать остаток новой строки

        System.out.println("Введите название книги для поиска:");
        String title = scanner.nextLine();
        System.out.println("Результаты поиска:");
        library.findBooksByTitle(title).forEach(System.out::println);
    }

    private void findBookByAuthor() {
        scanner.nextLine(); // Считать остаток новой строки

        System.out.println("Введите автора книги для поиска:");
        String author = scanner.nextLine();
        System.out.println("Результаты поиска:");
        library.findBooksByAuthor(author).forEach(System.out::println);
    }

    private void findBookByYear() {
        System.out.println("Введите год издания книги для поиска:");
        int year = scanner.nextInt();
        scanner.nextLine(); // Считать остаток новой строки

        System.out.println("Результаты поиска:");
        library.findBooksByYear(year).forEach(System.out::println);
    }

    private void displayAllBooks() {
        System.out.println("Список всех книг:");
        library.displayAllBooks();
    }
}