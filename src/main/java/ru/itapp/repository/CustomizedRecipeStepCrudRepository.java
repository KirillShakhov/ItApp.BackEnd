package ru.itapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itapp.entity.RecipeStep;


@Repository
public interface CustomizedRecipeStepCrudRepository extends CrudRepository<RecipeStep, Long> {

}
