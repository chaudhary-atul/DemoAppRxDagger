package com.example.mildly.demoapprxdagger.data.service;

import com.example.mildly.demoapprxdagger.data.pojo.Recipe;
import com.example.mildly.demoapprxdagger.data.pojo.Recipes;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipeService {

    @GET("search")
    Observable<Recipes> getRecipes(
            @Query("key") String key,
            @Query("count") int count,
            @Query("page") int page,
            @Query("sort") String sort
    );

    @GET("get")
    Observable<Recipe> getRecipe(
            @Query("key") String key,
            @Query("rId") String recipeId

    );
}
