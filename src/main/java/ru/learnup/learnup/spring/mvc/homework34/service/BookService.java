package ru.learnup.learnup.spring.mvc.homework34.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.learnup.learnup.spring.mvc.homework34.entity.Book;
import ru.learnup.learnup.spring.mvc.homework34.mapper.BookMapper;
import ru.learnup.learnup.spring.mvc.homework34.model.BookDto;
import ru.learnup.learnup.spring.mvc.homework34.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository repository;
    private final BookMapper bookMapper;

    public BookService(BookRepository repository, BookMapper bookMapper) {
        this.repository = repository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> getBooks() {
        return repository.findAll().stream()
                .map(bookMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public BookDto findById(int id) {
        return bookMapper.mapToDto(repository.getById(id));
    }

    @Transactional
    public BookDto createBook(BookDto book) {
        Book entity = bookMapper.mapToEntity(book);
        repository.save(entity);
        return bookMapper.mapToDto(entity);
    }

    public void delete(int id) {
        Book book = repository.getById(id);
        repository.delete(book);
    }
}
