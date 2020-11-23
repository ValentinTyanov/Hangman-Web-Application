package com.hangman.repository;

import com.hangman.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityRepository extends JpaRepository<User, Integer> {

  User findByEmail(String email);
}
