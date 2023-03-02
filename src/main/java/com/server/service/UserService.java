package com.server.service;

import com.server.api.dto.RegistrationBody;
import com.server.exception.UserAlreadyExistsException;
import com.server.model.LocalUser;
import com.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public LocalUser registerUser(RegistrationBody body) throws UserAlreadyExistsException {
        if(userRepository.findByUsernameIgnoreCase(body.getUsername()).isPresent() ||
        userRepository.findByEmailIgnoreCase(body.getEmail()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        LocalUser user=new LocalUser();
        user.setUsername(body.getUsername());
        user.setFirstName(body.getFirstName());
        user.setLastName(body.getLastName());
        user.setEmail(body.getEmail());
        //encrypted password
        user.setPassword(body.getPassword());
        return userRepository.save(user);
    }
}
