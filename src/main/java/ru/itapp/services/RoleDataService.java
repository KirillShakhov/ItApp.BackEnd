package ru.itapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itapp.entity.Role;
import ru.itapp.repository.CustomizedRoleCrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class RoleDataService {
    private final CustomizedRoleCrudRepository customizedRoleCrudRepository;

    @Autowired
    public RoleDataService(CustomizedRoleCrudRepository customizedRoleCrudRepository) {
        this.customizedRoleCrudRepository = customizedRoleCrudRepository;
    }

    @Transactional
    public List<Role> findAll() {
        return (List<Role>) customizedRoleCrudRepository.findAll();
    }

    @Transactional
    public void save(Role role) {
        customizedRoleCrudRepository.save(role);
    }

    @Transactional
    public void removeById(Long id) {
        customizedRoleCrudRepository.deleteById(id);
    }

    @Transactional
    public Optional<Role> getById(Long id) {
        return customizedRoleCrudRepository.findById(id);
    }
}

