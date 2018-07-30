package com.example.mildly.demoapprxdagger.di.module;

import android.content.Context;

import com.example.mildly.demoapprxdagger.RxDaggerApplication;
import com.example.mildly.demoapprxdagger.data.AppDataManager;
import com.example.mildly.demoapprxdagger.data.Config;
import com.example.mildly.demoapprxdagger.data.DataManager;
import com.example.mildly.demoapprxdagger.data.service.ApiHelper;
import com.example.mildly.demoapprxdagger.data.service.AppApiHelper;
import com.example.mildly.demoapprxdagger.di.ApplicationContext;
import com.example.mildly.demoapprxdagger.di.RecipesBaseUrl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ApplicationModule {

    @Provides
    @ApplicationContext
    Context provideContext(RxDaggerApplication rxDaggerApplication) {
        return rxDaggerApplication.getApplicationContext();
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @RecipesBaseUrl
    String provideRecipesBaseUrl() {
        return Config.RECIPES_BASE_URL;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

}
