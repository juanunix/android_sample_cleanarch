package com.felipeporge.auction.presentation.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.felipeporge.auction.R;
import com.felipeporge.auction.presentation.views.activities.WalkthroughActivity;
import com.felipeporge.auction.presentation.views.fragments.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * This class represents a walkthrough screen explaining about the app.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public class WalkthroughFragment extends BaseFragment<WalkthroughActivity> {

    /* ButterKnife View Injections - BEGIN */

    @BindView(R.id.walkthrough_frag_img_iv)
    ImageView imgIv;

    @BindView(R.id.walkthrough_frag_title_tv)
    TextView titleTv;

    @BindView(R.id.walkthrough_frag_desc_tv)
    TextView descriptionTv;

    /* ButterKnife View Injections - END */

    private int mImageResId = 0;
    private String mTitle, mDescription;


    /**
     * Creates a new instance of {@link WalkthroughFragment}.
     * @param context The context.
     * @param imgResid The image resource id.
     * @param title The title string.
     * @param description The description string.
     * @return {@link WalkthroughFragment} instance.
     */
    public static WalkthroughFragment newInstance(Context context, int imgResid, String title, String description){

        WalkthroughFragment result = new WalkthroughFragment();
        result.mImageResId = imgResid;
        result.mTitle = title;
        result.mDescription = description;

        return result;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_walkthrough, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgIv.setImageResource(mImageResId);
        titleTv.setText(mTitle);
        descriptionTv.setText(mDescription);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    // NOTE: This listener is injected by ButterKnife.
    @OnClick(R.id.walkthrough_frag_close_bt)
    public void onCloseBtClicked(){
        getBaseActivity().finish();
    }

}