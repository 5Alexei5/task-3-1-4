package com.tereshin.pp314.services;


import com.tereshin.pp314.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;



public interface UserService extends UserDetailsService
 {

    Iterable<User> getAllUsers();

    void addUser(User user);

    void update(User user, long id);

    void deleteUser(long id);

    User getUser(String name);

    User getUser(Long id);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
