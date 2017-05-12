package com.felipeporge.auction.presentation.views.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.felipeporge.auction.R;
import com.felipeporge.auction.di.components.ItemDetailsViewComponent;
import com.felipeporge.auction.di.interfaces.HasComponent;
import com.felipeporge.auction.di.modules.views.ItemDetailsModule;
import com.felipeporge.auction.presentation.helpers.TimeHelper;
import com.felipeporge.auction.presentation.model.AuctionItemModel;
import com.felipeporge.auction.presentation.presenters.ItemDetailsViewPresenter;
import com.felipeporge.auction.presentation.views.activities.base.PresenterActivity;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * This class represents the screen to place a bid.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public class ItemDetailsActivity extends PresenterActivity<ItemDetailsViewPresenter>
        implements HasComponent<ItemDetailsViewComponent> {

    private static final String ITEM_EXTRA_KEY = "item";
    private static final String LOGGED_USER_ID_EXTRA_KEY = "usr";

    /* ButterKnife View Injections - BEGIN */

    @BindView(R.id.bid_item_bid_value_et)
    EditText bidValueEt;

    @BindView(R.id.bid_item_title_tv)
    TextView itemTitleTv;

    @BindView(R.id.bid_item_bid_count_tv)
    TextView bidCountTv;

    @BindView(R.id.bid_item_starting_bid_tv)
    TextView startingBidTv;

    @BindView(R.id.bid_item_image_sdv)
    SimpleDraweeView itemImgSdv;

    @BindView(R.id.bid_item_description_tv)
    TextView itemDescriptionTv;

    @BindView(R.id.bid_item_time_left_tv)
    TextView timeLeftTv;

    @BindView(R.id.bid_item_place_bid_bt)
    Button bidBt;

    @BindView(R.id.bid_item_sold_item_tv)
    TextView soldItemTv;


    /* ButterKnife View Injections - END */

    private AuctionItemModel mItemToBid;
    private long mLoggedUserId = -1;

    private DecimalFormat mPriceFormatter = new DecimalFormat("'$'0.00");
    private CountDownTimer mCountDownTimer;
    private ItemDetailsViewComponent mComponent;

    /**
     * Gets a new calling intent for this activity.
     * @param context The context.
     * @param loggedUserId Logged user id.
     * @param itemModel Item to edit.
     * @return Intent.
     */
    public static Intent getCallingIntent(Context context, long loggedUserId, AuctionItemModel itemModel){
        Intent intent = new Intent(context, ItemDetailsActivity.class);
        intent.putExtra(ITEM_EXTRA_KEY, itemModel);
        intent.putExtra(LOGGED_USER_ID_EXTRA_KEY, loggedUserId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid_item);

        getComponent().inject(this);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            mItemToBid = (AuctionItemModel) extras.get(ITEM_EXTRA_KEY);
            mLoggedUserId = extras.getLong(LOGGED_USER_ID_EXTRA_KEY, -1);
            if(mItemToBid == null || mLoggedUserId == -1){
                finish();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        itemTitleTv.setText(mItemToBid.getTitle());
        itemDescriptionTv.setText(mItemToBid.getDescription());
        startingBidTv.setText(mPriceFormatter.format(mItemToBid.getCurrentBidValue()));

        int bidCount = mItemToBid.getBidCount();
        if(bidCount == 1) {
            bidCountTv.setText(getString(R.string.row_auction_item_bid));
        } else {
            bidCountTv.setText(getString(R.string.row_auction_item_bids, bidCount));
        }

        long millisLeft = mItemToBid.getEndDateMillis() - System.currentTimeMillis();
        if(millisLeft < 0){
            showTimeFinished();
        }else{
            updateTimeLeft(millisLeft);
            mCountDownTimer = new CountDownTimer(millisLeft, TimeHelper.MINUTE_MS) {
                @Override
                public void onTick(long timeLeft) {
                    updateTimeLeft(timeLeft);
                }

                @Override
                public void onFinish() {
                    timeLeftTv.setText(getString(R.string.row_auction_finished));
                    timeLeftTv.setTextColor(ContextCompat.getColor(ItemDetailsActivity.this, R.color.red_800));
                    bidBt.setEnabled(false);
                }
            };
            mCountDownTimer.start();
        }

        try {
            itemImgSdv.setImageURI(Uri.parse(mItemToBid.getImg()));
        }catch(Exception e){
            Log.w(ItemDetailsActivity.class.getSimpleName(), "Invalid image url.");
        }
    }

    /**
     * Updates time left on ui.
     * @param timeLeft Time left in millis.
     */
    public void updateTimeLeft(long timeLeft){
        timeLeftTv.setText(TimeHelper.formatMillis(timeLeft));
        timeLeftTv.setTextColor(ContextCompat.getColor(ItemDetailsActivity.this, R.color.grey_600));
        bidBt.setEnabled(true);
    }

    /**
     * Shows on ui that the time was finished.
     */
    public void showTimeFinished(){
        timeLeftTv.setText(getString(R.string.row_auction_finished));
        timeLeftTv.setTextColor(ContextCompat.getColor(this, R.color.red_800));
        
        bidBt.setEnabled(false);
        bidValueEt.setEnabled(false);

        if(mItemToBid.getBidCount() > 0)
            soldItemTv.setText(getString(R.string.add_edit_item_sold_for,
                mPriceFormatter.format(mItemToBid.getCurrentBidValue())));
        else
            soldItemTv.setText(getString(R.string.add_edit_item_finished));
        soldItemTv.setVisibility(View.VISIBLE);
    }

    // NOTE: This listener is injected by ButterKnife.
    @OnClick(R.id.bid_item_place_bid_bt)
    public void onPlaceBidBtClicked(){
        getPresenter().onPlaceBid(mItemToBid, mLoggedUserId, bidValueEt.getText().toString());
    }

    @Override
    public ItemDetailsViewComponent getComponent() {
        if(mComponent == null) {
            mComponent = getBaseApplication().getComponent().plus(new ItemDetailsModule());
        }
        return mComponent;
    }
}
