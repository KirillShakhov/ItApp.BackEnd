package ru.itapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itapp.entity.Role;
import ru.itapp.entity.User;


@Repository
public interface CustomizedRoleCrudRepository extends CrudRepository<Role, Long> {
}
