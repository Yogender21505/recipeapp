package com.yogender.recipeapp.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.yogender.recipeapp.interfaces.Routes;
import com.yogender.recipeapp.model.FavouriteRecipe;
import com.yogender.recipeapp.model.Recipe;
import com.yogender.recipeapp.model.User;
import com.yogender.recipeapp.service.RecipeService;
import com.yogender.recipeapp.service.favouriterecipe.FavRecipeService;
import com.yogender.recipeapp.service.redis.RedisService;
import com.yogender.recipeapp.service.user.UserService;
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

    @Autowired
    private FavRecipeService favRecipeService;
    // Recipe---------------------------------------------------------
    @PostMapping("/recipe")
    @Override
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        Recipe newRecipe = service.createRecipe(recipe);
        redisService.updateRedisRecipe(newRecipe,60);
        return newRecipe;

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


    // User---------------------------------------------------------
    @Autowired
    private UserService userService;

    @PostMapping("/user/registration")
    @Override
    public boolean userRegistration(JsonNode requestBody) {
        if(!requestBody.has("username") ||  !requestBody.has("password") || !requestBody.has("email")){
            throw new IllegalArgumentException("Request body must contain 'username', 'password' and 'email' fields");
        }

        String username = requestBody.get("username").asText();
        String password = requestBody.get("password").asText();
        String email = requestBody.get("email").asText();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        userService.saveUser(user);
        return true;
    }

    @GetMapping("/user/login")
    @Override
    public String userLogin(JsonNode requestBody) {
        if(!requestBody.has("username") ||  !requestBody.has("password")){
            throw new IllegalArgumentException("Request body must contain 'username' and 'password' fields");
        }

        String username = requestBody.get("username").asText();
        String password = requestBody.get("password").asText();

        String token = userService.userLoginByUsernameAndPassword(username, password);

        if(token != null){
            return token;
        } else {
            throw new IllegalArgumentException("Invalid username or password");
        }
    }

    @GetMapping("/user/check")
    @Override
    public boolean isUserLoggedIn(JsonNode requestBody) {
        String token = requestBody.get("token").asText();
        if(token == null){
            throw new IllegalArgumentException("input token is null");
        }

        return userService.checkToken(token);
    }


    // User Recipe routes--------------------------------

    @GetMapping("/user/favouriterecipes")
    @Override
    public List<Recipe> getFavouriterRecipes(JsonNode requestBody) {
        boolean isValid = userService.checkToken(requestBody.get("token").asText());

        if(!requestBody.has("userid")){
            throw new IllegalArgumentException("Request body must contain 'userid' fields");
        }
        if(!isValid){
            throw new IllegalArgumentException("Invalid token");
        }
        Long userid = requestBody.get("userid").asLong();
        return favRecipeService.getFavouriteRecipes(userid);
    }

    @PostMapping("/user/updatefavouriterecipes")
    @Override
    public boolean updateFavouriteRecipe(JsonNode requestBody) {
        boolean isValid = userService.checkToken(requestBody.get("token").asText());

        if(!requestBody.has("userid") || !requestBody.has("recipeid")){
            throw new IllegalArgumentException("Request body must contain 'userid' and 'recipeid' fields");
        }
        if(!isValid){
            throw new IllegalArgumentException("Invalid token");
        }
        Long userid = requestBody.get("userid").asLong();
        Long recipeid = requestBody.get("recipeid").asLong();
        FavouriteRecipe favouriteRecipe = new FavouriteRecipe();
        favouriteRecipe.setUserId(userid);
        favouriteRecipe.setRecipeId(recipeid);
        favRecipeService.UpdateFavouriteRecipe(favouriteRecipe);
        return true;
    }

}