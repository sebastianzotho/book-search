package com.books.book_search_app;

import com.books.book_search_app.controller.MenuController;
import com.books.book_search_app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.Scanner;

@SpringBootApplication
public class BookSearchAppApplication {

	@Autowired
	private BookService bookService;

	@Autowired
	private MenuController menuController;

	public static void main(String[] args) {
		SpringApplication.run(BookSearchAppApplication.class, args);
	}

	@PostConstruct
	public void init() {
		// Cargar libros de la API al iniciar la aplicación
		bookService.loadBooksFromAPI();
		// Mostrar el menú
		menuController.showMenu();
	}
}
