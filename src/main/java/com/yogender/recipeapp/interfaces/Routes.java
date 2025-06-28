package com.yogender.recipeapp.interfaces;

import com.fasterxml.jackson.databind.JsonNode;
import com.yogender.recipeapp.model.Recipe;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface Routes {
    public Recipe createRecipe(@RequestBody Recipe recipe);
    public List<Recipe> getAllRecipes();
    public Recipe getRecipe(@RequestBody JsonNode requestBody);
}
