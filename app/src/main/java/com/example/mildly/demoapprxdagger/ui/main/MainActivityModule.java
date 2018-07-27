package com.example.mildly.demoapprxdagger.ui.main;

import android.content.Context;

import com.example.mildly.demoapprxdagger.di.MainActivityContext;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    MainMvpPresenter<MainMvpView> provideMainMvpPresenter(MainPresenter<MainMvpView> presenter) {
        return presenter;
    }
}
