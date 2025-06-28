package com.yogender.recipeapp.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.yogender.recipeapp.interfaces.Routes;
import com.yogender.recipeapp.model.Recipe;
import com.yogender.recipeapp.service.RecipeService;
import com.yogender.recipeapp.service.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mysql.cj.conf.PropertyKey.logger;

@RestController
@RequestMapping("/api")
public class ApiController implements Routes {

    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
    @Autowired
    private RecipeService service;

    @Autowired
    private RedisService redisService;

    @PostMapping("/recipe")
    @Override
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return service.createRecipe(recipe);
    }

    @GetMapping("/recipes")
    @Override
    public List<Recipe> getAllRecipes() {
        return service.getAllRecipes();
    }

    @GetMapping("/recipe")
    @Override
    public Recipe getRecipe(@RequestBody JsonNode requestBody) {
        if (!requestBody.has("id")) {
            throw new IllegalArgumentException("Request body must contain 'id' field");
        }

        Long id = requestBody.get("id").asLong();
        logger.info("Received request to fetch recipe with ID: {}", id);

        Recipe recipe = redisService.findRecipeById(id);
        if (recipe != null) {
            logger.info("Recipe found in REDIS: {}", recipe.getName());
            return recipe;
        }

        logger.info("Recipe not found in REDIS: Fetching from Database");
        recipe = service.getRecipeById(id);

        if (recipe != null) {
            logger.info("Recipe fetched from Database: {}", recipe.getName());
            redisService.updateRedisRecipe(recipe);
        } else {
            logger.warn("Recipe not found in Database");
        }

        return recipe;
    }
}