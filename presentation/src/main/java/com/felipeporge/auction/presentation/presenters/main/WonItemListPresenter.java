package com.felipeporge.auction.presentation.presenters.main;

import com.felipeporge.auction.R;
import com.felipeporge.auction.domain.entities.AuctionItem;
import com.felipeporge.auction.domain.interactors.base.UseCaseCallback;
import com.felipeporge.auction.domain.interactors.items.GetWonItemsUseCase;
import com.felipeporge.auction.presentation.mapper.ItemModelMapper;
import com.felipeporge.auction.presentation.model.AuctionItemModel;
import com.felipeporge.auction.presentation.presenters.base.Presenter;
import com.felipeporge.auction.presentation.views.fragments.main.WonItemListFragment;

import java.util.ArrayList;

/**
 * This class represents the won item list presenter.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 24/11/2016
 */
public class WonItemListPresenter extends Presenter<WonItemListFragment> {

    private ItemModelMapper mMapper;
    private GetWonItemsUseCase mGetWonItemsUseCase;

    /**
     * Constructor method.
     * @param getWonItemsUseCase - Get All user won Items Use Case.
     */
    public WonItemListPresenter(ItemModelMapper mapper, GetWonItemsUseCase getWonItemsUseCase){
        mMapper = mapper;
        mGetWonItemsUseCase = getWonItemsUseCase;
    }

    @Override
    public void resume() {
        loadWonItems();
    }

    @Override
    public void pause() {}

    @Override
    public void destroy() {}

    /**
     * Loads won items.
     */
    public void loadWonItems(){

        if(!isViewValid())
            return;

        getView().showLoading();
        mGetWonItemsUseCase.execute(getView().getLoggedUserId(), new UseCaseCallback<ArrayList<AuctionItem>>() {
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
                getView().showToast(getViewContext().getString(R.string.won_items_failure));
            }
        });

    }
}
