package ru.itapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itapp.entity.Recipe;


@Repository
public interface CustomizedRecipeCrudRepository extends CrudRepository<Recipe, Long> {

}
