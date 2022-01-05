package ru.itapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itapp.entity.Profile;


@Repository
public interface CustomizedProfileCrudRepository extends CrudRepository<Profile, Long> {
}
