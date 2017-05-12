package com.felipeporge.auction.di.components;

import com.felipeporge.auction.di.interfaces.ActivityScope;
import com.felipeporge.auction.di.interfaces.DaggerComponent;
import com.felipeporge.auction.di.modules.views.MainModule;
import com.felipeporge.auction.presentation.views.activities.MainActivity;
import com.felipeporge.auction.presentation.views.fragments.main.AuctionItemListFragment;
import com.felipeporge.auction.presentation.views.fragments.main.BiddingItemListFragment;
import com.felipeporge.auction.presentation.views.fragments.main.StoreFragment;
import com.felipeporge.auction.presentation.views.fragments.main.WonItemListFragment;

import dagger.Subcomponent;

/**
 * This class represents the main view module.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 9/23/16
 */
@ActivityScope
@Subcomponent(modules = {MainModule.class})
public interface MainViewComponent extends DaggerComponent {

    void inject(MainActivity mainActivity);

    void inject(AuctionItemListFragment auctionItemListFragment);

    void inject(BiddingItemListFragment biddingItemListFragment);

    void inject(WonItemListFragment wonItemListFragment);

    void inject(StoreFragment storeFragment);
}
