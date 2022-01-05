package ru.itapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itapp.entity.Ingredients;


@Repository
public interface CustomizedIngredientsCrudRepository extends CrudRepository<Ingredients, Long> {

}
