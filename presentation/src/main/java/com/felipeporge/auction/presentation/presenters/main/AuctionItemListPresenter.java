package com.felipeporge.auction.presentation.presenters.main;

import com.felipeporge.auction.R;
import com.felipeporge.auction.domain.entities.AuctionItem;
import com.felipeporge.auction.domain.interactors.base.UseCaseCallback;
import com.felipeporge.auction.domain.interactors.items.GetAllAuctionItemsUseCase;
import com.felipeporge.auction.presentation.mapper.ItemModelMapper;
import com.felipeporge.auction.presentation.model.AuctionItemModel;
import com.felipeporge.auction.presentation.presenters.base.Presenter;
import com.felipeporge.auction.presentation.views.fragments.main.AuctionItemListFragment;

import java.util.ArrayList;

/**
 * This class represents the auction item list view presenter.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 24/11/2016
 */
public class AuctionItemListPresenter extends Presenter<AuctionItemListFragment> {

    private ItemModelMapper mMapper;
    private GetAllAuctionItemsUseCase mAllAuctionItemsUc;

    /**
     * Constructor method.
     * @param getAllAuctionItemsUseCase - Get All Auction Items Use Case.
     */
    public AuctionItemListPresenter(ItemModelMapper mapper, GetAllAuctionItemsUseCase getAllAuctionItemsUseCase){
        mMapper = mapper;
        mAllAuctionItemsUc = getAllAuctionItemsUseCase;
    }

    @Override
    public void resume() {
        loadAuctionItems();
    }

    @Override
    public void pause() {}

    @Override
    public void destroy() {}

    /**
     * Loads auction items.
     */
    public void loadAuctionItems(){

        if(!isViewValid())
            return;

        getView().showLoading();
        mAllAuctionItemsUc.execute(getView().getLoggedUserId(), new UseCaseCallback<ArrayList<AuctionItem>>() {
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
                getView().showToast(getViewContext().getString(R.string.auction_items_failure));
            }
        });

    }
}
