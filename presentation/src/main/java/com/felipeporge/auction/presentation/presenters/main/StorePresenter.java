package com.felipeporge.auction.presentation.presenters.main;

import com.felipeporge.auction.R;
import com.felipeporge.auction.domain.entities.AuctionItem;
import com.felipeporge.auction.domain.interactors.base.UseCaseCallback;
import com.felipeporge.auction.domain.interactors.items.GetStoreItemsUseCase;
import com.felipeporge.auction.presentation.mapper.ItemModelMapper;
import com.felipeporge.auction.presentation.model.AuctionItemModel;
import com.felipeporge.auction.presentation.presenters.base.Presenter;
import com.felipeporge.auction.presentation.views.fragments.main.StoreFragment;

import java.util.ArrayList;

/**
 * This class represents the store view presenter.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 24/11/2016
 */
public class StorePresenter extends Presenter<StoreFragment> {

    private ItemModelMapper mMapper;
    private GetStoreItemsUseCase mGetStoreItemsUseCase;

    /**
     * Constructor method.
     * @param getStoreItemsUseCase - Get user's store items.
     */
    public StorePresenter(ItemModelMapper mapper, GetStoreItemsUseCase getStoreItemsUseCase){
        mMapper = mapper;
        mGetStoreItemsUseCase = getStoreItemsUseCase;
    }

    @Override
    public void resume() {
        loadStoreItems();
    }

    @Override
    public void pause() {}

    @Override
    public void destroy() {}

    /**
     * Loads store items.
     */
    public void loadStoreItems(){

        if(!isViewValid())
            return;

        getView().showLoading();
        mGetStoreItemsUseCase.execute(getView().getLoggedUserId(), new UseCaseCallback<ArrayList<AuctionItem>>() {
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
                getView().showToast(getViewContext().getString(R.string.store_failure));
            }
        });

    }
}
