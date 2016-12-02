package com.felipeporge.sample.presentation.views.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.felipeporge.sample.R;
import com.felipeporge.sample.presentation.helpers.ResourceHelper;
import com.felipeporge.sample.presentation.views.fragments.WalkthroughFragment;

/**
 * This class represents a walkthrough view pager adapter.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public class WalkthroughAdapter extends FragmentStatePagerAdapter {

    private Context mContext;
    private String[] mTitles, mDescriptions, mImages;

    /**
     * Constructor method.
     * @param fm FragmentManager instance.
     * @param context The context.
     */
    public WalkthroughAdapter(FragmentManager fm, Context context) {
        super(fm);

        mContext = context;

        mTitles = context.getResources().getStringArray(R.array.walkthrough_titles);
        mDescriptions = context.getResources().getStringArray(R.array.walkthrough_descriptions);
        mImages = context.getResources().getStringArray(R.array.walkthrough_images);
    }

    /**
     * This method returns the fragment for the every ViewPager position.
     */
    @Override
    public Fragment getItem(int position) {
        return WalkthroughFragment.newInstance(
                mContext,
                ResourceHelper.getResourceId(mContext, mImages[position], "drawable"),
                mTitles[position],
                mDescriptions[position]
        );
    }

    /**
     * This method returns page count.
     */
    @Override
    public int getCount() {
        int count = mTitles.length;

        if(mDescriptions.length < count)
            count = mDescriptions.length;

        if(mImages.length < count)
            count = mImages.length;

        return count;
    }
}
