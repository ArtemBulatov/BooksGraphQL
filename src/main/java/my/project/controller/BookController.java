package my.project.controller;

import lombok.RequiredArgsConstructor;
import my.project.entity.Author;
import my.project.entity.Book;
import my.project.service.AuthorService;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import my.project.service.BookService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;

    @MutationMapping
    public Book saveBook(@Argument String title, @Argument List<Author> authors) {
        List<Author> savedAuthors = new ArrayList<>();
        authors.forEach(author -> savedAuthors.add(authorService.saveAuthor(author)));
        return bookService.saveBook(title, savedAuthors);
    }

    @QueryMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @QueryMapping
    public List<Book> getBooksByAuthor(@Argument Author author) {
        if (author.getId() != null) {
            return authorService.getAuthor(author.getId()).getBooks();
        }
        return authorService.getAuthor(author.getName()).getBooks();
    }
}
