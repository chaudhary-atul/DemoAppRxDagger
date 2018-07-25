package com.example.mildly.demoapprxdagger.di.module;

import android.content.Context;

import com.example.mildly.demoapprxdagger.RxDaggerApplication;
import com.example.mildly.demoapprxdagger.di.ApplicationContext;

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

}
