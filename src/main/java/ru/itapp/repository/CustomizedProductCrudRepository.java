package ru.itapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itapp.entity.Product;


@Repository
public interface CustomizedProductCrudRepository extends CrudRepository<Product, Long> {

}
