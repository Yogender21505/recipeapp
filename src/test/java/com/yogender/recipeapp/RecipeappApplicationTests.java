package com.yogender.recipeapp;

import com.yogender.recipeapp.service.RecipeService;
import com.yogender.recipeapp.service.redis.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class RecipeappApplicationTests {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RedisService redisService;

    @Test
    void contextLoads() {
        // This test ensures the application context loads successfully
    }
}
