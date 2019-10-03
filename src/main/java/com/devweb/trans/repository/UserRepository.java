package com.devweb.trans.repository;

import com.devweb.trans.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository //dit a sprint cree moi un objet et les garde dans authauwides
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    //@Query("SELECT u FROM USer u WHERE u.login IS NULL")
    //public List<User> users();
}

//