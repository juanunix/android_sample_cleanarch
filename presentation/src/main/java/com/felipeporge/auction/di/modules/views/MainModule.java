package com.felipeporge.auction.di.modules.views;

import com.felipeporge.auction.di.interfaces.ActivityScope;
import com.felipeporge.auction.domain.interactors.items.GetAllAuctionItemsUseCase;
import com.felipeporge.auction.domain.interactors.items.GetBiddingItemsUseCase;
import com.felipeporge.auction.domain.interactors.items.GetStoreItemsUseCase;
import com.felipeporge.auction.domain.interactors.items.GetWonItemsUseCase;
import com.felipeporge.auction.presentation.mapper.ItemModelMapper;
import com.felipeporge.auction.presentation.presenters.main.AuctionItemListPresenter;
import com.felipeporge.auction.presentation.presenters.main.BiddingItemListPresenter;
import com.felipeporge.auction.presentation.presenters.main.StorePresenter;
import com.felipeporge.auction.presentation.presenters.main.WonItemListPresenter;

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
    AuctionItemListPresenter providesAuctionItemListPresenter(ItemModelMapper mapper, GetAllAuctionItemsUseCase getAllAuctionItemsUc){
        return new AuctionItemListPresenter(mapper, getAllAuctionItemsUc);
    }

    @Provides
    @ActivityScope
    BiddingItemListPresenter providesBiddingItemListPresenter(ItemModelMapper mapper, GetBiddingItemsUseCase getBiddingItemsUseCase){
        return new BiddingItemListPresenter(mapper, getBiddingItemsUseCase);
    }

    @Provides
    @ActivityScope
    WonItemListPresenter providesWonItemListPresenter(ItemModelMapper mapper, GetWonItemsUseCase getWonItemsUseCase){
        return new WonItemListPresenter(mapper, getWonItemsUseCase);
    }

    @Provides
    @ActivityScope
    StorePresenter providesStorePresenter(ItemModelMapper mapper, GetStoreItemsUseCase getStoreItemsUseCase){
        return new StorePresenter(mapper, getStoreItemsUseCase);
    }

    /* PRESENTER PROVIDERS - END */
}
