package com.example.mildly.demoapprxdagger.ui.main.recipedetail;


import com.example.mildly.demoapprxdagger.ui.base.MvpPresenter;

public interface RecipeDetailMvpPresenter<V extends RecipeDetailMvpView> extends MvpPresenter<V> {
    void fetchRecipeDetail(String recipeId);
}
