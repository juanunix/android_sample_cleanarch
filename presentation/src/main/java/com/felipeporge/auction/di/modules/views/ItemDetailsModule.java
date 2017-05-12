package com.felipeporge.auction.di.modules.views;

import com.felipeporge.auction.di.interfaces.ActivityScope;
import com.felipeporge.auction.domain.interactors.bids.PlaceBidUseCase;
import com.felipeporge.auction.presentation.mapper.BidModelMapper;
import com.felipeporge.auction.presentation.presenters.ItemDetailsViewPresenter;

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
    ItemDetailsViewPresenter providesItemDetailsViewPresenter(BidModelMapper mapper, PlaceBidUseCase placeBidUseCase){
        return new ItemDetailsViewPresenter(mapper, placeBidUseCase);
    }

    /* PRESENTER PROVIDERS - END */
}
