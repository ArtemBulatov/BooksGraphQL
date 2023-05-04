package my.graphql.project.service;

import lombok.RequiredArgsConstructor;
import my.graphql.project.entity.Author;
import my.graphql.project.entity.Book;
import my.graphql.project.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book saveBook(String tittle, List<Author> authors) {
        Book book = findOrCreate(tittle);
        book.setAuthors(authors);
        return bookRepository.save(book);
    }

    public Book saveBook(Book book) {
        if (book.getId() == null) {
            return findOrCreate(book.getTitle());
        }
        return bookRepository.save(book);
    }

    public Book findOrCreate(String title) {
        return bookRepository.findBookByTitle(title).orElseGet(() -> create(title));
    }

    private Book create(String title) {
        Book book = new Book();
        book.setTitle(title);
        return bookRepository.save(book);
    }
}
