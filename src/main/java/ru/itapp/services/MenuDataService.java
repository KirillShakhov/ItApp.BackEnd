package ru.itapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itapp.entity.Menu;
import ru.itapp.entity.Recipe;
import ru.itapp.repository.CustomizedMenuCrudRepository;
import ru.itapp.repository.CustomizedRecipeCrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class MenuDataService {
    private final CustomizedMenuCrudRepository customizedMenuCrudRepository;

    @Autowired
    public MenuDataService(CustomizedMenuCrudRepository customizedMenuCrudRepository) {
        this.customizedMenuCrudRepository = customizedMenuCrudRepository;
    }

    @Transactional
    public List<Menu> findAll() {
        return (List<Menu>) customizedMenuCrudRepository.findAll();
    }

    @Transactional
    public void save(Menu menu) {
        customizedMenuCrudRepository.save(menu);
    }

    @Transactional
    public void removeById(Long id) {
        customizedMenuCrudRepository.deleteById(id);
    }

    @Transactional
    public Optional<Menu> getById(Long id) {
        return customizedMenuCrudRepository.findById(id);
    }
}

