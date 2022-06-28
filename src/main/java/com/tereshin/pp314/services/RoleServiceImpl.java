package com.tereshin.pp314.services;

import com.tereshin.pp314.models.Role;
import com.tereshin.pp314.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getListRoles(String... roles) {
        List<Role> roleList = new ArrayList<>();
        for (String role : roles) {
            Role r = roleRepository.findByAuthority(role);
            if (r == null) {
                r = new Role(role);
            }
            roleList.add(r);
        }
        return roleList;
    }

    @Override
    public Iterable<Role> getRoles() {
        return roleRepository.findAll();
    }
}
