package com.felipeporge.auction.presentation.presenters;

import com.felipeporge.auction.R;
import com.felipeporge.auction.domain.entities.AuctionBid;
import com.felipeporge.auction.domain.interactors.base.UseCaseCallback;
import com.felipeporge.auction.domain.interactors.bids.PlaceBidUseCase;
import com.felipeporge.auction.presentation.mapper.BidModelMapper;
import com.felipeporge.auction.presentation.model.AuctionBidModel;
import com.felipeporge.auction.presentation.model.AuctionItemModel;
import com.felipeporge.auction.presentation.presenters.base.Presenter;
import com.felipeporge.auction.presentation.views.activities.ItemDetailsActivity;

/**
 * This class represents the item details view presenter.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public class ItemDetailsViewPresenter extends Presenter<ItemDetailsActivity> {

    private BidModelMapper mMapper;
    private PlaceBidUseCase mPlaceBidUseCase;

    /**
     * Constructor method.
     * @param mapper Bid model mapper.
     * @param placeBidUseCase Use case to place a bid for an item.
     */
    public ItemDetailsViewPresenter(BidModelMapper mapper, PlaceBidUseCase placeBidUseCase) {
        mMapper = mapper;
        mPlaceBidUseCase = placeBidUseCase;
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
     * On place a bid.
     * @param itemToBid Item to bid.
     * @param loggedUserId Logged user id.
     * @param valueStr Bid value.
     */
    public void onPlaceBid(AuctionItemModel itemToBid, long loggedUserId, String valueStr) {
        if(!isViewValid())
            return;

        long currentTime = System.currentTimeMillis();
        if(itemToBid.getEndDateMillis() - currentTime < 0){
            getView().showToast(getViewContext().getString(R.string.bid_item_time_finished));
            getView().showTimeFinished();
            return;
        }

        float value;
        try {
            value = Float.parseFloat(valueStr);
            if(value < itemToBid.getCurrentBidValue()){
                throw new Exception();
            }
        }catch(Exception e){
            getView().showToast(getViewContext().getString(R.string.bid_item_invalid_bid_value));
            return;
        }

        getView().showLoading();
        AuctionBidModel bid = new AuctionBidModel();
        bid.setItemId(itemToBid.getId());
        bid.setUserId(loggedUserId);
        bid.setValue(value);
        bid.setCreatedAt(currentTime);

        mPlaceBidUseCase.execute(mMapper.transform(bid), new UseCaseCallback<AuctionBid>() {
            @Override
            public void onSuccess(AuctionBid auctionBid) {
                getView().hideLoading();
                getView().showToast(getViewContext().getString(R.string.bid_item_success));
                getView().finish();
            }

            @Override
            public void onFailure(Exception exception) {
                getView().hideLoading();
                getView().showToast(getViewContext().getString(R.string.bid_item_failure));
            }
        });
    }
}
