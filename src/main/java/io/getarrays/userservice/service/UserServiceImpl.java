package io.getarrays.userservice.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.getarrays.userservice.domain.Role;
import io.getarrays.userservice.domain.User;
import io.getarrays.userservice.repo.RoleRepo;
import io.getarrays.userservice.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService , UserDetailsService{

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final  PasswordEncoder passwordEncoder;
    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(username);
        if(user == null){
            log.error("User not found in databases");
            throw new UsernameNotFoundException("User not found in databases");
        }else{
            log.info("User found in databases {}" ,username);
        }
        Collection<SimpleGrantedAuthority>  authorities = new ArrayList<>();
        user.getRoles().forEach(role ->{authorities.add(new SimpleGrantedAuthority(role.getName()));});
        return  new org.springframework.security.core.userdetails.User(user.getUsername (), user.getPassword(), authorities);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Add new role to user");

        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findRoleByName(roleName);
        user.getRoles().add(role);
        
    }

    @Override
    public User getUser(String username) {
        log.info("Get user by username");
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("List of users");
        return userRepo.findAll();
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Save role");
        return roleRepo.save(role);
    }

    @Override
    public User saveUser(User user) {
        log.info("Save user");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

  
    
}