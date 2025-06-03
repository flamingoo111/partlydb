package com.example.usersdb.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomUserVacancyRepository extends JpaRepository<Vacancy, Integer> {
    @Query(value = "SELECT v.* FROM vacancy v WHERE v.id = ANY(SELECT unnest(u.saved_id) FROM users u WHERE u.login = :login)", nativeQuery = true)
    List<Vacancy> findVacanciesByUserLogin(@Param("login") String login);
}