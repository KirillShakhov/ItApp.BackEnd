package ru.itapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itapp.entity.User;
import ru.itapp.repository.CustomizedUserCrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class UserDataService {
    private final CustomizedUserCrudRepository customizedUserCrudRepository;

    @Autowired
    public UserDataService(CustomizedUserCrudRepository customizedUserCrudRepository) {
        this.customizedUserCrudRepository = customizedUserCrudRepository;
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

