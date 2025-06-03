package com.example.usersdb.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VacancyRepository extends CrudRepository<Vacancy, Integer> {
    @Query("SELECT v FROM Vacancy v WHERE LOWER(v.name) LIKE LOWER(:name) OR LOWER(v.name) LIKE LOWER(CONCAT(:name, '%'))")
    Iterable<Vacancy> findByNameMatchingOrStartingWith(@Param("name") String name);
}