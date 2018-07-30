package com.example.mildly.demoapprxdagger.ui.main;

import com.example.mildly.demoapprxdagger.ui.main.recipedetail.RecipeDetailFragment;
import com.example.mildly.demoapprxdagger.ui.main.recipedetail.RecipeDetailModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentProvider {

    @ContributesAndroidInjector(modules = RecipeDetailModule.class)
    abstract RecipeDetailFragment provideRecipeDetailFragmentFactory();
}
