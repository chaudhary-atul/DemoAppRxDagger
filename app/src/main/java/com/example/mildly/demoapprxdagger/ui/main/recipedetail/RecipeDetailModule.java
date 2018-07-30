package com.example.mildly.demoapprxdagger.ui.main.recipedetail;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public class RecipeDetailModule {

    @Provides
    RecipeDetailMvpPresenter<RecipeDetailMvpView> provideRecipesDetailMvpPresenter(RecipeDetailPresenter<RecipeDetailMvpView> presenter) {
        return presenter;
    }
}
