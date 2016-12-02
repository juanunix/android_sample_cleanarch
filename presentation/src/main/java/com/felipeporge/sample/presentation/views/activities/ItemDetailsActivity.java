package com.felipeporge.sample.presentation.views.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.felipeporge.sample.R;
import com.felipeporge.sample.di.components.ItemDetailsViewComponent;
import com.felipeporge.sample.di.interfaces.HasComponent;
import com.felipeporge.sample.di.modules.views.ItemDetailsModule;
import com.felipeporge.sample.presentation.model.SampleItemModel;
import com.felipeporge.sample.presentation.presenters.ItemDetailsViewPresenter;
import com.felipeporge.sample.presentation.views.activities.base.PresenterActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * This class represents the screen to view item details.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public class ItemDetailsActivity extends PresenterActivity<ItemDetailsViewPresenter>
        implements HasComponent<ItemDetailsViewComponent> {

    private static final String ITEM_EXTRA_KEY = "item";

    /* ButterKnife View Injections - BEGIN */

    @BindView(R.id.item_details_title_tv)
    TextView itemTitleTv;

    @BindView(R.id.item_details_image_sdv)
    SimpleDraweeView itemImgSdv;

    @BindView(R.id.item_details_description_tv)
    TextView itemDescriptionTv;

    @BindView(R.id.item_details_close_bt)
    Button closeBt;


    /* ButterKnife View Injections - END */

    private SampleItemModel mItemToShow;

    private ItemDetailsViewComponent mComponent;

    /**
     * Gets a new calling intent for this activity.
     * @param context The context.
     * @param itemModel Item to edit.
     * @return Intent.
     */
    public static Intent getCallingIntent(Context context, SampleItemModel itemModel){
        Intent intent = new Intent(context, ItemDetailsActivity.class);
        intent.putExtra(ITEM_EXTRA_KEY, itemModel);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        getComponent().inject(this);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            mItemToShow = (SampleItemModel) extras.get(ITEM_EXTRA_KEY);
            if(mItemToShow == null){
                finish();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        itemTitleTv.setText(mItemToShow.getTitle());
        itemDescriptionTv.setText(mItemToShow.getDescription());

        try {
            itemImgSdv.setImageURI(Uri.parse(mItemToShow.getImgUrl()));
        }catch(Exception e){
            Log.w(ItemDetailsActivity.class.getSimpleName(), "Invalid image url.");
        }
    }


    // NOTE: This listener is injected by ButterKnife.
    @OnClick(R.id.item_details_close_bt)
    public void onCloseBtClicked(){
        finish();
    }

    @Override
    public ItemDetailsViewComponent getComponent() {
        if(mComponent == null) {
            mComponent = getBaseApplication().getComponent().plus(new ItemDetailsModule());
        }
        return mComponent;
    }
}
