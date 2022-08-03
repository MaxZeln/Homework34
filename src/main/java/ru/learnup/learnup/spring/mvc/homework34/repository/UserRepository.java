package ru.learnup.learnup.spring.mvc.homework34.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.learnup.learnup.spring.mvc.homework34.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public User getUserByNickname(String nickname);

}
