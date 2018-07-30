package com.example.mildly.demoapprxdagger.di.module;

import com.example.mildly.demoapprxdagger.ui.main.MainActivity;
import com.example.mildly.demoapprxdagger.ui.main.MainActivityModule;
import com.example.mildly.demoapprxdagger.ui.main.MainFragmentProvider;
import com.example.mildly.demoapprxdagger.ui.main.recipedetail.RecipeDetailModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {MainActivityModule.class, RecipeDetailModule.class})
    abstract MainActivity bindMainActivity();

}
