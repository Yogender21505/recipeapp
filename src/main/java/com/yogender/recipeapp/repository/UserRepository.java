package com.yogender.recipeapp.repository;

import com.yogender.recipeapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.username = :username and u.password = :password")
    User findByUsername(String username);
}
