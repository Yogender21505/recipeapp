package com.yogender.recipeapp.repository;

import com.yogender.recipeapp.model.Recipe;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository  extends JpaRepository<Recipe, Long> {
    @Query("SELECT name, description, ingredientName " +
            "FROM Recipe" +
            "WHERE id = :id")
    Recipe findRecipesById(Long id);
}
