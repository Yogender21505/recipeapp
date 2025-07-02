package com.yogender.recipeapp.repository;

import com.yogender.recipeapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.elasticsearch.annotations.Query;


public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select * from user where username = :username and password = :password")
    User findByUsername(String username);
}
