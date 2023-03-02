package com.server.service;

import com.server.api.dto.LoginBody;
import com.server.api.dto.RegistrationBody;
import com.server.exception.UserAlreadyExistsException;
import com.server.model.LocalUser;
import com.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private JWTService jwtService;

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
        user.setPassword(encryptionService.encryptPassword(body.getPassword()));
        return userRepository.save(user);
    }

    public String logIn(LoginBody body){
        Optional<LocalUser> user=userRepository.findByUsernameIgnoreCase(body.getUsername());
        if(user.isPresent()){
            LocalUser localUser=user.get();
            if(encryptionService.verifyPassword(body.getPassword(),localUser.getPassword())){
                return jwtService.generateJWT(localUser);
            }
        }
        return null;
    }
}
