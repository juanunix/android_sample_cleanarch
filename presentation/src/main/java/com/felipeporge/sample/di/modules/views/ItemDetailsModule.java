package com.felipeporge.sample.di.modules.views;

import com.felipeporge.sample.di.interfaces.ActivityScope;
import com.felipeporge.sample.presentation.presenters.ItemDetailsViewPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * This class represents provides all Item details view dependencies.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 24/11/2016
 */
@Module
public class ItemDetailsModule {

    /* PRESENTER PROVIDERS - BEGIN */

    @Provides
    @ActivityScope
    ItemDetailsViewPresenter providesItemDetailsViewPresenter(){
        return new ItemDetailsViewPresenter();
    }

    /* PRESENTER PROVIDERS - END */
}
