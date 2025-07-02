package com.yogender.recipeapp.repository;

import com.yogender.recipeapp.model.FavouriteRecipe;
import com.yogender.recipeapp.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavouriteRecipeRepository extends JpaRepository<FavouriteRecipe,Long> {

    @Query("SELECT r FROM Recipe r WHERE r.id IN (SELECT fr.recipeId FROM FavouriteRecipe fr WHERE fr.userId = :userId)")
    List<Recipe> getFavouriteRecipeByUserId(Long userId);
}

