package com.example.mildly.demoapprxdagger.data.service;

import com.example.mildly.demoapprxdagger.data.pojo.Recipes;
import com.example.mildly.demoapprxdagger.data.pojo.SingleRecipe;

import io.reactivex.Observable;

public interface ApiHelper {
    Observable<Recipes> getRecipes(int page);

    Observable<SingleRecipe> getRecipe(String recipeId);
}
