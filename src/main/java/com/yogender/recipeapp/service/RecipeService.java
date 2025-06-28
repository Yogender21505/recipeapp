package com.yogender.recipeapp.service;

import com.yogender.recipeapp.model.Recipe;
import com.yogender.recipeapp.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public List<Recipe> getAllRecipes(){
        return recipeRepository.findAll();
    }
    public Recipe getRecipeById(Long id){
        return recipeRepository.findRecipesById(id);
    }
}

