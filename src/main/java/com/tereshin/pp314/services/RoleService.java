package com.tereshin.pp314.services;

import com.tereshin.pp314.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> getListRoles(String... roles);

    Iterable<Role> getRoles();
}
