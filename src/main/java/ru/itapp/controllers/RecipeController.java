package ru.itapp.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itapp.entity.Recipe;
import ru.itapp.entity.View;
import ru.itapp.services.RecipeDataService;
import ru.itapp.services.UserDataService;

import java.util.*;

@RestController
public class RecipeController {
    private final UserDataService userDataService;
    private final RecipeDataService recipeDataService;

    @Autowired
    public RecipeController(UserDataService userDataService, RecipeDataService recipeDataService) {
        this.userDataService = userDataService;
        this.recipeDataService = recipeDataService;
    }

    @JsonView(View.Recipe.class)
    @GetMapping("/recipes")
    public Map<String, Object> recipes(@RequestParam(value = "find", required = false) String find) {
        Map<String, Object> map = new ManagedMap<>();
        map.put("status", "ok");
        try {
            if(find == null) {
                List<Recipe> recipes = recipeDataService.findAll();
                map.put("list", recipes);
            }else{
                List<Recipe> recipes = recipeDataService.findByNameIsContaining(find.toLowerCase());
                map.put("list", recipes);
            }
            return map;
        } catch (Exception e) {
            map.put("status", "error");
            map.put("message", e.getMessage());
            return map;
        }
    }
    @JsonView(View.RecipeAllInfo.class)
    @GetMapping("/recipes/info")
    public Map<String, Object> recipes(@RequestParam(value = "id") Long id) {
        Map<String, Object> map = new ManagedMap<>();
        map.put("status", "ok");
        try {
            Optional<Recipe> recipe = recipeDataService.getById(id);
            map.put("item", recipe);
            return map;
        } catch (Exception e) {
            map.put("status", "error");
            map.put("message", e.getMessage());
            return map;
        }
    }
}