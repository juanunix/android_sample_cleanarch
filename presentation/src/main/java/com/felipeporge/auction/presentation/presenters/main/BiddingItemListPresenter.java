package com.felipeporge.auction.presentation.presenters.main;

import com.felipeporge.auction.R;
import com.felipeporge.auction.domain.entities.AuctionItem;
import com.felipeporge.auction.domain.interactors.base.UseCaseCallback;
import com.felipeporge.auction.domain.interactors.items.GetBiddingItemsUseCase;
import com.felipeporge.auction.presentation.mapper.ItemModelMapper;
import com.felipeporge.auction.presentation.model.AuctionItemModel;
import com.felipeporge.auction.presentation.presenters.base.Presenter;
import com.felipeporge.auction.presentation.views.fragments.main.BiddingItemListFragment;

import java.util.ArrayList;

/**
 * This class represents the bidding item list view presenter.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 24/11/2016
 */
public class BiddingItemListPresenter extends Presenter<BiddingItemListFragment> {

    private ItemModelMapper mMapper;
    private GetBiddingItemsUseCase mGetBiddingItemsUc;

    /**
     * Constructor method.
     * @param getBiddingItemsUseCase - Get bidding items use case.
     */
    public BiddingItemListPresenter(ItemModelMapper mapper, GetBiddingItemsUseCase getBiddingItemsUseCase){
        mMapper = mapper;
        mGetBiddingItemsUc = getBiddingItemsUseCase;
    }

    @Override
    public void resume() {
        loadBiddingItems();
    }

    @Override
    public void pause() {}

    @Override
    public void destroy() {}

    /**
     * Loads auction items.
     */
    public void loadBiddingItems(){

        if(!isViewValid())
            return;

        getView().showLoading();
        mGetBiddingItemsUc.execute(getView().getLoggedUserId(), new UseCaseCallback<ArrayList<AuctionItem>>() {
            @Override
            public void onSuccess(ArrayList<AuctionItem> auctionItems) {
                ArrayList<AuctionItemModel> items = mMapper.parseBack(auctionItems);
                getView().hideLoading();
                if(items.size() > 0) {
                    getView().showItems(items);
                }else {
                    getView().showNoItems();
                }
            }

            @Override
            public void onFailure(Exception exception) {
                getView().hideLoading();
                getView().showToast(getViewContext().getString(R.string.bidding_failure));
            }
        });
    }
}
