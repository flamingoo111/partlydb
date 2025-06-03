package com.example.usersdb.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
public interface SavedRepository extends JpaRepository<Users, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE users SET saved_id = ARRAY[:id] WHERE login = :login", nativeQuery = true)
    void updateSavedIdByLogin(@Param("id") Integer[] id, @Param("login") String login);
}