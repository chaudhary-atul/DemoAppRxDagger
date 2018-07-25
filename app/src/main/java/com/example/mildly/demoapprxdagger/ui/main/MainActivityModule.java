package com.example.mildly.demoapprxdagger.ui.main;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    private MainActivity mainActivity;

    @Provides
    Context provideMainActivityContext(MainActivity mainActivity){
        this.mainActivity=mainActivity;
        return mainActivity;
    }

 /*   @Provides
    HomeMvpPresenter<HomeMvpView> provideHomeMvpPresenter(HomePresenter<HomeMvpView> presenter) {
        return presenter;
    }*/
}
