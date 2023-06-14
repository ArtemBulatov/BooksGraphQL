package my.project.controller;

import lombok.RequiredArgsConstructor;
import my.project.entity.Author;
import my.project.entity.Book;
import my.project.service.AuthorService;
import my.project.service.BookService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class AuthorController {
    private final AuthorService authorService;
    private final BookService bookService;

    @MutationMapping
    public Author saveAuthor(@Argument String name, @Argument List<Book> books) {
        List<Book> savedBooks = books.stream().map(bookService::saveBook).collect(Collectors.toList());
        return authorService.saveAuthor(name, savedBooks);
    }

    @QueryMapping
    public Author getAuthor(@Argument String name) {
        return authorService.getAuthor(name);
    }
}
