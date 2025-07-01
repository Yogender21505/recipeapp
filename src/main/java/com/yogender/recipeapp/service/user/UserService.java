package com.yogender.recipeapp.service.user;

import com.yogender.recipeapp.controller.ApiController;
import com.yogender.recipeapp.model.User;
import com.yogender.recipeapp.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Value("${SECRET_KEY}")
    private String SECRET_KEY;

    @Value("${EXPIRATION_TIME}")
    private long EXPIRATION_TIME;

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user){
        userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public String userLoginByUsernameAndPassword(String username, String password) {
        User user = userRepository.findByUsername(username);
        logger.debug("username: {}, password: {}", user.getUsername(), user.getPassword());
        if (user != null && (password.equals(user.getPassword()))) {
            logger.debug("TOKEN username: {}, password: {}", user.getUsername(), user.getPassword());
            return generateToken(user);
        }
        return null;
    }

    private String generateToken(User user) {
        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();

        logger.debug("TOKEN username: {}, token: {}", user.getUsername(), token);
        return token;
    }

    public String getUsernameFromToken(String token) {
        try{
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
            return claims.getSubject();
        } catch (Exception e){
            return null;
        }
    }

    public boolean checkToken(String token) {
        String username = getUsernameFromToken(token);
        if(username == null){
            return false;
        }
        if(userRepository.findByUsername(username) != null){
            return true;
        }
        return false;
    }

}
