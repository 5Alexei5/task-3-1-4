package com.tereshin.pp314.services;

import com.tereshin.pp314.models.Role;
import com.tereshin.pp314.models.User;
import com.tereshin.pp314.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(User user, long id) {
        user.setId(id);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUser(String name) {
        return userRepository.findByEmail(name);
    }

    @Override
    public User getUser(Long id) {

        return userRepository.findById(id).get();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.getAuthorities());
    }

    @PostConstruct
    private void createAdmin() {
        User user = userRepository.findByEmail("admin.@mail.ru");
        if (user == null) {
            user = new User();
            Role roleAdmin = new Role("ROLE_ADMIN");
            Role roleUser = new Role("ROLE_USER");
            List<Role> list = new ArrayList<>();
            list.add(roleAdmin);
            list.add(roleUser);
            user.setEmail("admin");
            user.setRoles(list);
            user.setPassword("$2a$12$iFnXFFHtMk/0RuCoaijFc.i77pubSJlVgqe3E1IJn2zea1z7PBr9S");
            userRepository.save(user);
        }
    }
}
