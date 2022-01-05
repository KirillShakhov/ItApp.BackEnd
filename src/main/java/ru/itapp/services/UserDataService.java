package ru.itapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itapp.entity.Role;
import ru.itapp.entity.User;
import ru.itapp.exeptions.EmailAlreadyExistsException;
import ru.itapp.exeptions.UsernameAlreadyExistsException;
import ru.itapp.repository.CustomizedUserCrudRepository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class UserDataService {
    private final CustomizedUserCrudRepository customizedUserCrudRepository;

    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtTokenProvider tokenProvider;

    @Autowired
    public UserDataService(CustomizedUserCrudRepository customizedUserCrudRepository) {
        this.customizedUserCrudRepository = customizedUserCrudRepository;
    }

    public String loginUser(String username, String password) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));

        return tokenProvider.generateToken(authentication);
    }

    public User registerUser(User user, Role role) {
        log.info("registering user {}", user.getUsername());

        if(customizedUserCrudRepository.existsById(user.getUsername())) {
            log.warn("username {} already exists.", user.getUsername());

            throw new UsernameAlreadyExistsException(
                    String.format("username %s already exists", user.getUsername()));
        }

        if(customizedUserCrudRepository.existsByEmail(user.getEmail())) {
            log.warn("email {} already exists.", user.getEmail());

            throw new EmailAlreadyExistsException(
                    String.format("email %s already exists", user.getEmail()));
        }
        user.setActive(true);
        user.setPass(passwordEncoder.encode(user.getPass()));
        user.setRoles(new HashSet<>() {{
            add(role);
        }});

        return customizedUserCrudRepository.save(user);
    }

    @Transactional
    public List<User> findAll() {
        return (List<User>) customizedUserCrudRepository.findAll();
    }

    @Transactional
    public void save(User user) {
        customizedUserCrudRepository.save(user);
    }

    @Transactional
    public void removeById(String username) {
        customizedUserCrudRepository.deleteById(username);
    }

    @Transactional
    public Optional<User> getById(String username) {
        return customizedUserCrudRepository.findById(username);
    }
}

