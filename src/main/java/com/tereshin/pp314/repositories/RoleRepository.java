package com.tereshin.pp314.repositories;


import com.tereshin.pp314.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByAuthority(String name);
}
