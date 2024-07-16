package com.books.book_search_app.service;

import com.books.book_search_app.model.Book;
import com.books.book_search_app.model.GutendexResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final RestTemplate restTemplate;
    private List<Book> books;

    public BookService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void loadBooksFromAPI() {
        String url = "https://gutendex.com/books/";
        GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);
        this.books = response.getResults();
    }

    public List<Book> searchBooksByTitle(String title) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public List<String> getAllAuthors() {
        return books.stream()
                .flatMap(book -> book.getAuthors().stream())
                .map(author -> author.getName())
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> getLivingAuthorsByYear(int year) {
        // Implementación específica para obtener autores vivos en un año determinado
        // Esto requeriría más información sobre los autores (fecha de nacimiento y muerte)
        return List.of();  // Retorna una lista vacía temporalmente
    }

    public List<Book> getBooksByLanguage(String language) {
        // Implementación específica para obtener libros por idioma
        // Esto requeriría más información sobre los libros (idioma)
        return List.of();  // Retorna una lista vacía temporalmente
    }
}



