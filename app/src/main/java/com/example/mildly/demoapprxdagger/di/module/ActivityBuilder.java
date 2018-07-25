package com.example.mildly.demoapprxdagger.di.module;

import com.example.mildly.demoapprxdagger.ui.main.MainActivity;
import com.example.mildly.demoapprxdagger.ui.main.MainActivityModule;

import dagger.android.ContributesAndroidInjector;

public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();

}
