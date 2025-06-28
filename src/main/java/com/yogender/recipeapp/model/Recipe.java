package com.yogender.recipeapp.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "recipe")
public class Recipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String ingredients;

    // Default constructor required by JPA
    public Recipe() {
    }

    // Constructor with parameters
    public Recipe(String name, String description, String ingredients) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ingredients='" + ingredients + '\'' +
                '}';
    }
}