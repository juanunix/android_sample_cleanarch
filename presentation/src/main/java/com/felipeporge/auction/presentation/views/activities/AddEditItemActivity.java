package com.felipeporge.auction.presentation.views.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.felipeporge.auction.R;
import com.felipeporge.auction.di.components.AddEditItemViewComponent;
import com.felipeporge.auction.di.interfaces.HasComponent;
import com.felipeporge.auction.di.modules.views.AddEditItemModule;
import com.felipeporge.auction.presentation.model.AuctionItemModel;
import com.felipeporge.auction.presentation.presenters.AddEditItemViewPresenter;
import com.felipeporge.auction.presentation.views.activities.base.PresenterActivity;
import com.felipeporge.auction.presentation.views.dialogs.ChangeImageDialog;
import com.felipeporge.auction.presentation.views.dialogs.base.OnDialogResultListener;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * This class represents the activity to add a new item to the auction bar.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public class AddEditItemActivity extends PresenterActivity<AddEditItemViewPresenter>
        implements HasComponent<AddEditItemViewComponent> {

    public static final int MODE_ADD = 0;
    public static final int MODE_EDIT = 1;

    private static final String MODE_EXTRA_KEY = "mode";
    private static final String LOGGED_USER_ID_EXTRA_KEY = "loggedUser";
    private static final String ITEM_EXTRA_KEY = "itemId";

    /* ButterKnife View Injections - BEGIN */

    @BindView(R.id.add_edit_item_desc_et)
    EditText descriptionEt;

    @BindView(R.id.add_edit_item_title_et)
    EditText titleEt;

    @BindView(R.id.add_edit_item_starting_bid_et)
    EditText startingBidEt;

    @BindView(R.id.add_edit_item_image_sdv)
    SimpleDraweeView itemImgSdv;

    @BindView(R.id.add_item_save_bt)
    Button saveBt;

    @BindView(R.id.add_item_change_image_fl)
    FrameLayout changeImgFl;

    @BindView(R.id.add_item_sold_item_tv)
    TextView soldItemTv;

    /* ButterKnife View Injections - END */

    private AddEditItemViewComponent mComponent;
    private DecimalFormat mPriceFormatter = new DecimalFormat("'$'0.00");

    private int mMode = MODE_ADD;

    private long mLoggedUserId = -1;
    private AuctionItemModel mItemToEdit;

    private String mImg;

    /**
     * Gets a new calling intent for this activity.
     * @param context The context.
     * @param userId Logged user id.
     * @return Intent.
     */
    public static Intent getAddCallingIntent(Context context, long userId){
        Intent intent = new Intent(context, AddEditItemActivity.class);
        intent.putExtra(MODE_EXTRA_KEY, MODE_ADD);
        intent.putExtra(LOGGED_USER_ID_EXTRA_KEY, userId);
        return intent;
    }

    /**
     * Gets a new calling intent for this activity.
     * @param context The context.
     * @param itemModel Item to edit.
     * @return Intent.
     */
    public static Intent getEditCallingIntent(Context context, AuctionItemModel itemModel){
        Intent intent = new Intent(context, AddEditItemActivity.class);
        intent.putExtra(MODE_EXTRA_KEY, MODE_EDIT);
        intent.putExtra(ITEM_EXTRA_KEY, itemModel);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_item);

        getComponent().inject(this);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            mMode = extras.getInt(MODE_EXTRA_KEY, MODE_ADD);

            switch(mMode){
                case MODE_ADD:
                    setTitle(R.string.add_edit_item_add_title);
                    mLoggedUserId = extras.getLong(LOGGED_USER_ID_EXTRA_KEY, -1);

                    if(mLoggedUserId == -1){
                        finish();
                    }
                    break;

                case MODE_EDIT:
                    setTitle(R.string.add_edit_item_edit_title);
                    mItemToEdit = (AuctionItemModel) extras.get(ITEM_EXTRA_KEY);

                    if(mItemToEdit == null){
                        finish();
                    }
                    break;
            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mMode == MODE_EDIT) {
            titleEt.setText(mItemToEdit.getTitle());
            descriptionEt.setText(mItemToEdit.getDescription());
            startingBidEt.setText(String.valueOf(mItemToEdit.getStartingBid()));
            mImg = mItemToEdit.getImg();
            loadImage(mItemToEdit.getImg());

            if(mItemToEdit.getEndDateMillis() <= System.currentTimeMillis()){
                showFinished();
            }
        }
    }

    /**
     * Shows like sold item.
     */
    public void showFinished(){
        if(mItemToEdit.getBidCount() > 0) {
            changeImgFl.setEnabled(false);
            changeImgFl.setAlpha(0.5f);

            titleEt.setEnabled(false);
            descriptionEt.setEnabled(false);
            startingBidEt.setEnabled(false);

            soldItemTv.setText(getString(R.string.add_edit_item_sold_for,
                    mPriceFormatter.format(mItemToEdit.getCurrentBidValue())));

            saveBt.setEnabled(false);
        }else {
            soldItemTv.setText(getString(R.string.add_edit_item_finished));
        }
        soldItemTv.setVisibility(View.VISIBLE);
    }

    /**
     * Loads an image.
     * @param imgUrl Image url.
     */
    public void loadImage(String imgUrl){
        try {
            itemImgSdv.setImageURI(Uri.parse(imgUrl));
        }catch(Exception e){
            Log.w(AddEditItemActivity.class.getSimpleName(), "Invalid image url.");
        }
    }

    // NOTE: This listener is injected by ButterKnife.
    @OnClick(R.id.add_item_change_image_fl)
    public void onChangeImageFlClicked(){
        showDialog(ChangeImageDialog.getInstance(mImg, new OnDialogResultListener<String>(){
            @Override
            public void onResult(String result) {
                mImg = result;
                loadImage(mImg);
            }
        }));
    }

    // NOTE: This listener is injected by ButterKnife.
    @OnClick(R.id.add_item_save_bt)
    public void onSaveBtClicked(){
        String title = titleEt.getText().toString();
        String description = descriptionEt.getText().toString();
        String startingBid = startingBidEt.getText().toString();

        switch(mMode){
            case MODE_ADD:
                getPresenter().onCreateItem(title, description, startingBid, mImg);
                break;

            case MODE_EDIT:
                getPresenter().onUpdateItem(mItemToEdit.getId(), title, description, startingBid, mImg);
                break;
        }
    }

    /**
     * Gets logged user id.
     * @return Logged user id.
     */
    public long getItemOwnerUserId() {
        switch(mMode){
            default:
            case MODE_ADD:
                return mLoggedUserId;

            case MODE_EDIT:
                return mItemToEdit.getUserId();
        }
    }

    @Override
    public AddEditItemViewComponent getComponent() {
        if (mComponent == null) {
            return getBaseApplication().getComponent().plus(new AddEditItemModule());
        }

        return mComponent;
    }
}
