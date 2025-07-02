package com.yogender.recipeapp.service.favouriterecipe;

import com.yogender.recipeapp.model.FavouriteRecipe;
import com.yogender.recipeapp.model.Recipe;
import com.yogender.recipeapp.repository.FavouriteRecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavRecipeService {

    @Autowired
    private FavouriteRecipeRepository favouriteRecipeRepository;

    public List<Recipe> getFavouriteRecipes(Long userId) {
        List<Recipe> favouriteRecipes = new ArrayList<>();
        favouriteRecipes = favouriteRecipeRepository.getFavouriteRecipeByUserId(userId);
        return favouriteRecipes;
    }

    public void UpdateFavouriteRecipe(FavouriteRecipe favouriteRecipe){
        favouriteRecipeRepository.save(favouriteRecipe);
    }
}
