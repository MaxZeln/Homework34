package ru.learnup.learnup.spring.mvc.homework34.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.learnup.learnup.spring.mvc.homework34.mapper.BookMapper;
import ru.learnup.learnup.spring.mvc.homework34.model.BookDto;
import ru.learnup.learnup.spring.mvc.homework34.service.BookService;
import ru.learnup.learnup.spring.mvc.homework34.view.BookView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    public BookController(BookService bookService,
                          BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @GetMapping
    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_USER\")")
    public List<BookView> getBooks() {
        List<BookDto> books = bookService.getBooks();
        return books.stream()
                .map(bookMapper::mapToView)
                .collect(Collectors.toList());
    }

    @GetMapping("/{bookId}")
    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_USER\")")
    public BookView getBook(@PathVariable(name = "bookId") int bookId) {
        BookDto book = bookService.findById(bookId);
        return bookMapper.mapToView(book);
    }

    @PostMapping
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    public BookView createBook(@RequestBody BookView book) {
        BookDto dto = bookMapper.mapFromView(book);
        return bookMapper.mapToView(
                bookService.createBook(dto)
        );
    }

    @PutMapping("/{bookId}")
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    public BookView updateAuthor(@PathVariable(name = "bookId") int authorId,
                                 @RequestBody BookView book) {
        BookDto dto = bookMapper.mapFromView(book);
        return bookMapper.mapToView(
                bookService.createBook(dto)
        );
    }

    @DeleteMapping("/{bookId}")
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    public void delete(@PathVariable(name = "bookId") int bookId) {
        bookService.delete(bookId);
    }
}