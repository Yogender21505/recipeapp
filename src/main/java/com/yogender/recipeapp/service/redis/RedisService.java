package com.yogender.recipeapp.service.redis;

import com.yogender.recipeapp.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void updateRedisRecipe(Recipe recipe) {
        if (recipe != null) {
            redisTemplate.opsForValue().set(recipe.getId().toString(), recipe, 10, TimeUnit.SECONDS);
        }
    }

    public void updateRedisRecipe(Recipe recipe, int ttl) {
        if (recipe != null) {
            redisTemplate.opsForValue().set(recipe.getId().toString(), recipe, ttl, TimeUnit.SECONDS);
        }
    }

    public Recipe findRecipeById(Long id) {
        if (id == null) {
            return null;
        }
        Recipe recipe = ((Recipe) redisTemplate.opsForValue().get(id.toString()));
        return recipe;
    }
}