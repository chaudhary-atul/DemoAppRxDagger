package com.example.mildly.demoapprxdagger.ui.main;

import com.example.mildly.demoapprxdagger.ui.base.MvpPresenter;

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void onViewPrepared();

    void fetchRecipes();

  //  void getRecipeFromRecipeId(String recipe_id);
}
