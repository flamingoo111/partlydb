package com.example.usersdb.user;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Integer> {
    Iterable<Users> findUsersByLogin(String login);
}