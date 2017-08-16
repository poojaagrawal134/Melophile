package com.vpaliy.melophile;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.vpaliy.melophile.di.component.ApplicationComponent;
import com.vpaliy.melophile.di.component.DaggerApplicationComponent;
import com.vpaliy.melophile.di.module.ApplicationModule;
import com.vpaliy.melophile.di.module.DataModule;
import com.vpaliy.melophile.di.module.InteractorModule;
import com.vpaliy.melophile.di.module.MapperModule;
import com.vpaliy.melophile.di.module.NetworkModule;

public class App extends Application {

    private ApplicationComponent applicationComponent;
    private static App INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
        INSTANCE = this;

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    private void initializeComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .dataModule(new DataModule())
                .networkModule(new NetworkModule())
                .mapperModule(new MapperModule())
                .interactorModule(new InteractorModule())
                .build();
    }

    public void setApplicationComponent(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }

    @NonNull
    public static App appInstance() {
        return INSTANCE;
    }

    public ApplicationComponent appComponent() {
        return applicationComponent;
    }
}