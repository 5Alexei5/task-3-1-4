package com.tereshin.pp314.repositories;

import com.tereshin.pp314.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);
    Optional<User> findById(Long id);

}
