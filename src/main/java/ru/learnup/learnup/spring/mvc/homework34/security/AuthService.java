package ru.learnup.learnup.spring.mvc.homework34.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.learnup.learnup.spring.mvc.homework34.entity.User;
import ru.learnup.learnup.spring.mvc.homework34.repository.UserRepository;

@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.getUserByNickname(username);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }

        return user;
    }
}
