package com.example.mildly.demoapprxdagger.di.module;

import com.example.mildly.demoapprxdagger.ui.main.MainActivity;
import com.example.mildly.demoapprxdagger.ui.main.MainActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();

}
