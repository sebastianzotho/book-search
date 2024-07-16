package com.books.book_search_app.controller;

import com.books.book_search_app.model.Book;
import com.books.book_search_app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Scanner;

@Controller
public class MenuController {

    @Autowired
    private BookService bookService;

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("Elija la opción a través de su número:");
            System.out.println("1- buscar libro por título");
            System.out.println("2- listar libros registrados");
            System.out.println("3- listar autores registrados");
            System.out.println("4- listar autores vivos en un determinado año");
            System.out.println("5- listar libro por idioma");
            System.out.println("0- salir");
            option = scanner.nextInt();
            scanner.nextLine();  // Consumir la nueva línea

            switch (option) {
                case 1:
                    System.out.print("Ingrese el título del libro: ");
                    String title = scanner.nextLine();
                    searchBookByTitle(title);
                    break;
                case 2:
                    listRegisteredBooks();
                    break;
                case 3:
                    listRegisteredAuthors();
                    break;
                case 4:
                    System.out.print("Ingrese el año: ");
                    int year = scanner.nextInt();
                    listLivingAuthorsByYear(year);
                    break;
                case 5:
                    System.out.print("Ingrese el idioma: ");
                    String language = scanner.nextLine();
                    listBooksByLanguage(language);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, elija una opción válida.");
            }
        } while (option != 0);
    }

    private void searchBookByTitle(String title) {
        // Lógica para buscar libro por título
        List<Book> books = bookService.searchBooksByTitle(title);
        for (Book book : books) {
            System.out.println(book.getTitle());
        }
    }

    private void listRegisteredBooks() {
        // Lógica para listar libros registrados
        List<Book> books = bookService.getAllBooks();
        for (Book book : books) {
            System.out.println(book.getTitle());
        }
    }

    private void listRegisteredAuthors() {
        // Lógica para listar autores registrados
        List<String> authors = bookService.getAllAuthors();
        for (String author : authors) {
            System.out.println(author);
        }
    }

    private void listLivingAuthorsByYear(int year) {
        // Lógica para listar autores vivos en un determinado año
        List<String> authors = bookService.getLivingAuthorsByYear(year);
        for (String author : authors) {
            System.out.println(author);
        }
    }

    private void listBooksByLanguage(String language) {
        // Lógica para listar libros por idioma
        List<Book> books = bookService.getBooksByLanguage(language);
        for (Book book : books) {
            System.out.println(book.getTitle());
        }
    }
}

