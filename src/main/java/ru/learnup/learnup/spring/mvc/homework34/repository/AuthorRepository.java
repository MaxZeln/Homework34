package ru.learnup.learnup.spring.mvc.homework34.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.learnup.learnup.spring.mvc.homework34.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
