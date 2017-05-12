package com.felipeporge.auction.presentation.views.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.felipeporge.auction.R;
import com.felipeporge.auction.presentation.views.activities.base.BaseActivity;
import com.felipeporge.auction.presentation.views.adapters.WalkthroughAdapter;

import butterknife.BindView;
import me.relex.circleindicator.CircleIndicator;

/**
 * This class represents the walkthrough activity.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public class WalkthroughActivity extends BaseActivity {

    /* ButterKnife View Injections - BEGIN */

    @BindView(R.id.walkthrough_pager)
    ViewPager viewPager;

    @BindView(R.id.walkthrough_circle_indicator)
    CircleIndicator circleIndicator;

    /* ButterKnife View Injections - END */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewPager.setAdapter(new WalkthroughAdapter(getSupportFragmentManager(), this));
        circleIndicator.setViewPager(viewPager);
    }

    @Override
    protected void onPause() {
        super.onPause();

        viewPager.setAdapter(null);
        viewPager.invalidate();
    }
}
