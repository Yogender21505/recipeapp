package com.yogender.recipeapp.interfaces;

import com.fasterxml.jackson.databind.JsonNode;
import com.yogender.recipeapp.model.Recipe;
import com.yogender.recipeapp.model.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface Routes {
    public Recipe createRecipe(@RequestBody Recipe recipe);
    public List<Recipe> getAllRecipes();
    public Recipe getRecipe(@RequestBody JsonNode requestBody);

    //Users
    public boolean userRegistration(@RequestBody JsonNode requestBody);
    public String userLogin(@RequestBody JsonNode requestBody);
    public boolean isUserLoggedIn(@RequestBody JsonNode requestBody);

    //user recipe
    public List<Recipe> getFavouriterRecipes(@RequestBody JsonNode requestBody);
    public boolean updateFavouriteRecipe(@RequestBody JsonNode requestBody);
}
