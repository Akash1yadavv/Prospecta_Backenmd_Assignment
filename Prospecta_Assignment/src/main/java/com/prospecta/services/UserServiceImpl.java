package com.prospecta.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.prospecta.dtos.LoginUserDto;
import com.prospecta.dtos.UserDto;
import com.prospecta.enums.Roles;
import com.prospecta.exceptions.UserException;
import com.prospecta.model.Role;
import com.prospecta.model.User;
import com.prospecta.repositories.RoleRepository;
import com.prospecta.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired private PasswordEncoder passwordEncoder;
    
    @Autowired private AuthenticationManager authenticationManager;
    
    @Autowired private UserRepository userRepository;
    
    @Autowired private RoleRepository roleRepository;

    @Override
    public User registerUser(UserDto userdto) throws UserException {
        if (userdto == null) {
            throw new UserException("user details cannot be null.");
        }

        Optional<Role> optionalRole = roleRepository.findByName(Roles.ADMIN);
        if (optionalRole.isEmpty()) {
            throw new UserException("Role 'ADMIN' not found.");
        }
        User user = new User();
        user.setRole(optionalRole.get());
        user.setPassword(passwordEncoder.encode(userdto.getPassword()));
        user.setEmail(userdto.getEmail());
        user.setName(userdto.getName());
        user.setPhone(userdto.getPhone());

        return userRepository.save(user);
    }

    @Override
    public User loginUser(LoginUserDto loginDetails) throws UserException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDetails.getEmail(), loginDetails.getPassword())
        );

        return userRepository.findByEmail(loginDetails.getEmail())
                .orElseThrow(() -> new UserException("Customer not found for email: " + loginDetails.getEmail()));
    }


}
