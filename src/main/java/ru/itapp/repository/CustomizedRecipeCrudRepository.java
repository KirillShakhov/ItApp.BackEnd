package ru.itapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itapp.entity.Recipe;

import java.util.List;


@Repository
public interface CustomizedRecipeCrudRepository extends CrudRepository<Recipe, Long> {
    List<Recipe> findByName(String name);
    List<Recipe> findByNameContainingIgnoreCase(String name);
}
