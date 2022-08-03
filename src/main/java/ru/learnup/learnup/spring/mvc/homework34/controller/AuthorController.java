package ru.learnup.learnup.spring.mvc.homework34.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.learnup.learnup.spring.mvc.homework34.mapper.AuthorMapper;
import ru.learnup.learnup.spring.mvc.homework34.model.AuthorDto;
import ru.learnup.learnup.spring.mvc.homework34.service.AuthorService;
import ru.learnup.learnup.spring.mvc.homework34.view.AuthorView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    public AuthorController(AuthorService authorService,
                            AuthorMapper authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @GetMapping
    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_USER\")")
    public List<AuthorView> getAuthors() {
        List<AuthorDto> authors = authorService.getAuthors();
        return authors.stream()
                .map(authorMapper::mapToView)
                .collect(Collectors.toList());
    }

    @GetMapping("/{authorId}")
    @PreAuthorize("hasRole(\"ROLE_ADMIN\") or hasRole(\"ROLE_USER\")")
    public AuthorView getAuthor(@PathVariable(name = "authorId") int authorId) {
        AuthorDto author = authorService.findById(authorId);
        return authorMapper.mapToView(author);
    }

    @PostMapping
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    public AuthorView createAuthor(@RequestBody AuthorView author) {
        AuthorDto dto = authorMapper.mapFromView(author);
        return authorMapper.mapToView(
                authorService.createAuthor(dto)
        );
    }

    @PutMapping("/{authorId}")
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    public AuthorView updateAuthor(@PathVariable(name = "authorId") int authorId,
                                   @RequestBody AuthorView author) {
//        SecurityContextHolder.getContext().getAuthentication().;
        AuthorDto dto = authorMapper.mapFromView(author);
        return authorMapper.mapToView(
                authorService.createAuthor(dto)
        );
    }

    @DeleteMapping("/{authorId}")
    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    public void delete(@PathVariable(name = "authorId") int authorId) {
        authorService.delete(authorId);
    }

}

