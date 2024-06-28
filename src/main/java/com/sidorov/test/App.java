package com.sidorov.test;

import com.sidorov.test.controller.LibraryController;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        LibraryController libraryController = new LibraryController();
        libraryController.runLibraryManagement();
    }
}
