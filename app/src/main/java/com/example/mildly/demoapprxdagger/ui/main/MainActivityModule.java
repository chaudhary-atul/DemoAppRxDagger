package com.example.mildly.demoapprxdagger.ui.main;

import android.content.Context;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.widget.LinearLayoutManager;

import com.example.mildly.demoapprxdagger.R;
import com.example.mildly.demoapprxdagger.data.pojo.Recipe;
import com.example.mildly.demoapprxdagger.data.pojo.Recipes;
import com.example.mildly.demoapprxdagger.di.ApplicationContext;
import com.example.mildly.demoapprxdagger.di.MainActivityContext;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    MainMvpPresenter<MainMvpView> provideMainMvpPresenter(MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

    @Provides
    @MainActivityContext
    MainActivity provideMainActivityContext(@MainActivityContext MainActivity mainActivity){
        return mainActivity;
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(@ApplicationContext Context context) {
        return new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
    }

    @Provides
    RecipesAdapter provideRecipeAdapter() {
        return new RecipesAdapter(new ArrayList<Recipe>());
    }

    @Provides
    CustomTabsIntent.Builder provideCustomTabBuilder(@ApplicationContext Context context){
        return new CustomTabsIntent.Builder()
                .addDefaultShareMenuItem()
                .setToolbarColor(context.getResources().getColor(R.color.colorPrimary))
                .setShowTitle(true);
    }

}
