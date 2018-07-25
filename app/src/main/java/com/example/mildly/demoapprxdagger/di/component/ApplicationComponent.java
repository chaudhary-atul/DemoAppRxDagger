package com.example.mildly.demoapprxdagger.di.component;


import com.example.mildly.demoapprxdagger.RxDaggerApplication;
import com.example.mildly.demoapprxdagger.di.module.ActivityBuilder;
import com.example.mildly.demoapprxdagger.di.module.ApplicationModule;
import com.example.mildly.demoapprxdagger.di.module.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBuilder.class,
        ApplicationModule.class,
        NetworkModule.class
})
public interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(RxDaggerApplication rxDaggerApplication);

        ApplicationComponent build();
    }

    void inject(RxDaggerApplication rxDaggerApplication);
}
