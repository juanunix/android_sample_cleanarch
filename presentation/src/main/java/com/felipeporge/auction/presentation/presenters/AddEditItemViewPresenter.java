package com.felipeporge.auction.presentation.presenters;

import com.felipeporge.auction.R;
import com.felipeporge.auction.config.Constants;
import com.felipeporge.auction.domain.entities.AuctionItem;
import com.felipeporge.auction.domain.interactors.base.UseCaseCallback;
import com.felipeporge.auction.domain.interactors.items.CreateItemUseCase;
import com.felipeporge.auction.domain.interactors.items.UpdateItemUseCase;
import com.felipeporge.auction.presentation.mapper.ItemModelMapper;
import com.felipeporge.auction.presentation.model.AuctionItemModel;
import com.felipeporge.auction.presentation.presenters.base.Presenter;
import com.felipeporge.auction.presentation.views.activities.AddEditItemActivity;

/**
 * This class represents the add and edit view presenter.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public class AddEditItemViewPresenter extends Presenter<AddEditItemActivity> {

    private CreateItemUseCase mCreateItemUc;
    private UpdateItemUseCase mUpdateItemUc;
    private ItemModelMapper mMapper;

    /**
     * Constructor method.
     * @param mapper Item model mapper.
     * @param createItemUc Use case to create an item.
     * @param updateItemUc Use case to update an item.
     */
    public AddEditItemViewPresenter(ItemModelMapper mapper, CreateItemUseCase createItemUc, UpdateItemUseCase updateItemUc){
        mMapper = mapper;
        mCreateItemUc = createItemUc;
        mUpdateItemUc = updateItemUc;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    /**
     * Handles the event of creating a new item.
     * @param title Item title.
     * @param description Item description.
     * @param startingBid Item starting bid.
     * @param img Item image.
     */
    public void onCreateItem(String title, String description, String startingBid, String img) {
        if(!isViewValid())
            return;

        if(title == null || title.length() < 3){
            getView().showToast(getViewContext().getString(R.string.add_edit_item_invalid_title));
            return;
        }


        if(description == null || description.length() < 10){
            getView().showToast(getViewContext().getString(R.string.add_edit_item_invalid_description));
            return;
        }

        float startingValue;
        try {
            startingValue = Float.parseFloat(startingBid);

            if(startingValue <= 0){
                throw new Exception();
            }
        }catch(Exception e){
            getView().showToast(getViewContext().getString(R.string.add_edit_item_invalid_starting_bid));
            return;
        }

        long finishAt = System.currentTimeMillis() + Constants.AUCTION_DURATION;
        AuctionItemModel itemModel = new AuctionItemModel(img, title, description, startingValue, finishAt);
        itemModel.setUserId(getView().getItemOwnerUserId());

        getView().showLoading();
        mCreateItemUc.execute(mMapper.transform(itemModel), new UseCaseCallback<AuctionItem>() {
            @Override
            public void onSuccess(AuctionItem auctionItem) {
                getView().hideLoading();
                getView().showToast(getViewContext().getString(R.string.add_edit_item_successfully_saved));
                getView().finish();
            }

            @Override
            public void onFailure(Exception exception) {
                getView().hideLoading();
                getView().showToast(getViewContext().getString(R.string.add_edit_item_saving_failure));
            }
        });
    }

    /**
     * Updates an item.
     * @param id Item id.
     * @param title New item title.
     * @param description New item description
     * @param startingBid New item starting bid.
     * @param img New item image.
     */
    public void onUpdateItem(long id, String title, String description, String startingBid, String img) {
        if(!isViewValid())
            return;

        if(title == null || title.length() < 3){
            getView().showToast(getViewContext().getString(R.string.add_edit_item_invalid_title));
            return;
        }


        if(description == null || description.length() < 10){
            getView().showToast(getViewContext().getString(R.string.add_edit_item_invalid_description));
            return;
        }

        float startingValue;
        try {
            startingValue = Float.parseFloat(startingBid);

            if(startingValue <= 0){
                throw new Exception();
            }
        }catch(Exception e){
            getView().showToast(getViewContext().getString(R.string.add_edit_item_invalid_starting_bid));
            return;
        }

        long finishAt = System.currentTimeMillis() + Constants.AUCTION_DURATION;
        AuctionItemModel itemModel = new AuctionItemModel(img, title, description, startingValue, finishAt);
        itemModel.setId(id);
        itemModel.setUserId(getView().getItemOwnerUserId());

        getView().showLoading();
        mUpdateItemUc.execute(mMapper.transform(itemModel), new UseCaseCallback<AuctionItem>() {
            @Override
            public void onSuccess(AuctionItem auctionItem) {
                getView().hideLoading();
                getView().showToast(getViewContext().getString(R.string.add_edit_item_successfully_saved));
                getView().finish();
            }

            @Override
            public void onFailure(Exception exception) {
                getView().hideLoading();
                getView().showToast(getViewContext().getString(R.string.add_edit_item_saving_failure));
            }
        });
    }
}
