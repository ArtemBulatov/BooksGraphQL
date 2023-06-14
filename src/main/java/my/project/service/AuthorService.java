package my.project.service;

import lombok.RequiredArgsConstructor;
import my.project.entity.Author;
import my.project.entity.Book;
import my.project.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public Author getAuthor(String name) {
        return authorRepository.findByName(name).orElse(null);
    }

    public Author getAuthor(UUID id) {
        return authorRepository.getReferenceById(id);
    }

    public Author saveAuthor(String name, List<Book> books) {
        Author author = findOrCreate(name);
        author.setBooks(books);
        return authorRepository.save(author);
    }

    public Author saveAuthor(Author author) {
        if (author.getId() == null) {
            return findOrCreate(author.getName());
        }
        return authorRepository.save(author);
    }

    public Author findOrCreate(String name) {
        return authorRepository.findByName(name).orElseGet(() -> create(name));
    }

    private Author create(String name) {
        Author author = new Author();
        author.setName(name);
        return authorRepository.save(author);
    }
}
