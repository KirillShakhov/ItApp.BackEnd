package ru.itapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itapp.entity.Menu;
import ru.itapp.entity.Recipe;

import java.util.List;


@Repository
public interface CustomizedMenuCrudRepository extends CrudRepository<Menu, Long> {
}
