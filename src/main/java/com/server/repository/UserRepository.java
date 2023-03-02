package com.server.repository;

import com.server.model.Address;
import com.server.model.LocalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<LocalUser,Long> {
    Optional<LocalUser> findByUsernameIgnoreCase(String username);

    Optional<LocalUser> findByEmailIgnoreCase(String email);

}
