package com.example.mildly.demoapprxdagger.data;

import com.example.mildly.demoapprxdagger.data.pojo.Recipes;
import com.example.mildly.demoapprxdagger.data.pojo.SingleRecipe;
import com.example.mildly.demoapprxdagger.data.service.ApiHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppDataManager implements DataManager {

    private final ApiHelper apiHelper;

    @Inject
    public AppDataManager(ApiHelper apiHelper) {
        this.apiHelper = apiHelper;
    }

    @Override
    public Observable<Recipes> getRecipes(int page) {
        return apiHelper.getRecipes(page);
    }

    @Override
    public Observable<SingleRecipe> getRecipe(String recipeId) {
        return apiHelper.getRecipe(recipeId);
    }


}
