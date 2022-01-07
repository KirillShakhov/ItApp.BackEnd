package ru.itapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itapp.entity.Recipe;
import ru.itapp.repository.CustomizedRecipeCrudRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class RecipeDataService {
    private final CustomizedRecipeCrudRepository customizedRecipeCrudRepository;

    @Autowired
    public RecipeDataService(CustomizedRecipeCrudRepository customizedRecipeCrudRepository) {
        this.customizedRecipeCrudRepository = customizedRecipeCrudRepository;
    }

    @Transactional
    public List<Recipe> findAll() {
        return (List<Recipe>) customizedRecipeCrudRepository.findAll();
    }

    @Transactional
    public List<Recipe> findByName(String name) {return customizedRecipeCrudRepository.findByName(name);}

    @Transactional
    public void save(Recipe recipe) {
        customizedRecipeCrudRepository.save(recipe);
    }

    @Transactional
    public void removeById(Long id) {
        customizedRecipeCrudRepository.deleteById(id);
    }

    @Transactional
    public Optional<Recipe> getById(Long id) {
        return customizedRecipeCrudRepository.findById(id);
    }
}

