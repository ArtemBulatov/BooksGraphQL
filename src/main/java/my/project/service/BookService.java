package my.project.service;

import lombok.RequiredArgsConstructor;
import my.project.entity.Author;
import my.project.entity.Book;
import my.project.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookStoreGRPC storeGRPC;

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
        storeGRPC.saveBook(book);
        return bookRepository.save(book);
    }
}
