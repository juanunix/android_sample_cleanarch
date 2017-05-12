package com.felipeporge.auction.di.modules.views;

import com.felipeporge.auction.di.interfaces.ActivityScope;
import com.felipeporge.auction.domain.interactors.items.CreateItemUseCase;
import com.felipeporge.auction.domain.interactors.items.UpdateItemUseCase;
import com.felipeporge.auction.presentation.mapper.ItemModelMapper;
import com.felipeporge.auction.presentation.presenters.AddEditItemViewPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * This class represents the add and edit view module.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
@Module
public class AddEditItemModule {

    /* PRESENTER PROVIDERS - BEGIN */

    @Provides
    @ActivityScope
    AddEditItemViewPresenter providesAddEditItemViewPresenter(ItemModelMapper mapper, CreateItemUseCase createItemUseCase, UpdateItemUseCase updateItemUseCase){
        return new AddEditItemViewPresenter(mapper, createItemUseCase, updateItemUseCase);
    }

    /* PRESENTER PROVIDERS - END */
}
