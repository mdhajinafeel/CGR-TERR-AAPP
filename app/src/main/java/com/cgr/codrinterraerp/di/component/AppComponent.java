package com.cgr.codrinterraerp.di.component;

import android.app.Application;

import com.cgr.codrinterraerp.CodrinTerraErpApplication;
import com.cgr.codrinterraerp.di.modules.ApiModule;
import com.cgr.codrinterraerp.di.modules.AppModule;
import com.cgr.codrinterraerp.di.modules.DBModule;
import com.cgr.codrinterraerp.di.modules.RepoModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, DBModule.class, AppModule.class, ApiModule.class, RepoModule.class})
public interface AppComponent {
    void inject(CodrinTerraErpApplication application);

    @Component.Factory
    interface Factory {
        AppComponent create(@BindsInstance Application application);
    }
}