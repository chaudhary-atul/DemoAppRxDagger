package com.example.mildly.demoapprxdagger.data.service;

import com.example.mildly.demoapprxdagger.data.Config;
import com.example.mildly.demoapprxdagger.data.pojo.Recipe;
import com.example.mildly.demoapprxdagger.data.pojo.Recipes;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppApiHelper implements ApiHelper{
    private final RecipeService recipeService;

    @Inject
    public AppApiHelper(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Override
    public Observable<Recipes> getRecipes(int page) {
        return recipeService.getRecipes(Config.RECIPES_API_KEY, 20,page, "r");
    }

    @Override
    public Observable<Recipe> getRecipe(String recipeId) {
        return recipeService.getRecipe(Config.RECIPES_API_KEY, recipeId);
    }
}
