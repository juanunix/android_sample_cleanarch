package com.felipeporge.sample.di.modules.views;

import com.felipeporge.sample.di.interfaces.ActivityScope;
import com.felipeporge.sample.domain.interactors.items.GetAllItemsUseCase;
import com.felipeporge.sample.presentation.mapper.ItemModelMapper;
import com.felipeporge.sample.presentation.presenters.main.ItemListPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * This class represents the main module.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
@Module
public class MainModule {

    /* PRESENTER PROVIDERS - BEGIN */

    @Provides
    @ActivityScope
    ItemListPresenter providesAuctionItemListPresenter(ItemModelMapper mapper, GetAllItemsUseCase getAllitemsUseCase){
        return new ItemListPresenter(mapper, getAllitemsUseCase);
    }

    /* PRESENTER PROVIDERS - END */
}
