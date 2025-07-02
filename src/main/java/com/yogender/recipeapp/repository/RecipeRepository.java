package com.yogender.recipeapp.repository;

import com.yogender.recipeapp.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RecipeRepository  extends JpaRepository<Recipe, Long> {
    @Query("SELECT r.name, r.description, r.ingredients FROM Recipe r WHERE r.id = :id")
    Recipe findRecipesById(Long id);
}
