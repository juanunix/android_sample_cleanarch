package com.felipeporge.auction.di.modules;

import com.felipeporge.auction.BaseApplication;
import com.felipeporge.auction.data.repositories.PrefsRepositoryImpl;
import com.felipeporge.auction.domain.executors.PostExecutorThread;
import com.felipeporge.auction.domain.executors.TaskExecutor;
import com.felipeporge.auction.domain.interactors.prefs.GetShowedWalkthroughUseCase;
import com.felipeporge.auction.domain.interactors.prefs.PutShowedWalkthroughUseCase;
import com.felipeporge.auction.domain.repositories.PrefsRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * This class represents the module that provides all user management dependencies.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
@Module
public class PrefsModule {

    @Provides
    @Singleton
    PrefsRepository providesPrefsRepo(BaseApplication app){
        return new PrefsRepositoryImpl(app);
    }

    @Provides
    @Singleton
    GetShowedWalkthroughUseCase providesGetShowedWalkthroughUseCase(TaskExecutor executor, PostExecutorThread postExecutor, PrefsRepository repo){
        return new GetShowedWalkthroughUseCase(executor, postExecutor, repo);
    }

    @Provides
    @Singleton
    PutShowedWalkthroughUseCase providesPutShowedWalkthroughUseCase(TaskExecutor executor, PostExecutorThread postExecutor, PrefsRepository repo){
        return new PutShowedWalkthroughUseCase(executor, postExecutor, repo);
    }

}
