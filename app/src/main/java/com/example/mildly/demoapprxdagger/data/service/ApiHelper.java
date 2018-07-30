package com.example.mildly.demoapprxdagger.data.service;

import com.example.mildly.demoapprxdagger.data.pojo.Recipe;
import com.example.mildly.demoapprxdagger.data.pojo.Recipes;

import java.util.List;

import io.reactivex.Observable;

public interface ApiHelper {
    Observable<Recipes> getRecipes(int page);

    Observable<Recipe> getRecipe(String recipeId);
}
