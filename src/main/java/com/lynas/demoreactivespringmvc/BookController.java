package com.lynas.demoreactivespringmvc;


import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;


    public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    @GetMapping
    public Publisher<Book> getAll() {
        return bookRepository.findAll();
    }

    @PostMapping
    public Publisher<Book> insert(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @GetMapping("/author-name/{authorName}")
    public Publisher<Book> findByAuthor(@PathVariable String authorName) {
        return authorRepository.findByName(authorName)
                .flatMapMany(author -> bookRepository.findByAuthorId(author.getId()));
    }
}
