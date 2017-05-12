package com.felipeporge.auction.presentation.views.fragments.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.felipeporge.auction.BaseApplication;
import com.felipeporge.auction.presentation.views.activities.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * This class represents a base fragment.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public class BaseFragment<A extends BaseActivity> extends Fragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        if(!Fresco.hasBeenInitialized())
            Fresco.initialize(getBaseActivity());
    }

    /**
     * Gets base {@link android.support.v7.app.AppCompatActivity}.
     * @return {@link android.support.v7.app.AppCompatActivity} instance.
     */
    public A getBaseActivity(){
        return (A) getActivity();
    }

    /**
     * Sets activity title.
     * @param stringId - Resource string id.
     */
    public void setTitle(int stringId){
        getActivity().setTitle(stringId);
    }

    /**
     * Sets activity title.
     * @param title - New title..
     */
    public void setTitle(String title){
        getActivity().setTitle(title);
    }

    /**
     * Shows a fragment dialog.
     * @param dialog {@link DialogFragment} instance.
     */
    public void showDialog(DialogFragment dialog){
        getBaseActivity().showDialog(dialog);
    }

    /**
     * Gets base application instance.
     * @return {@link BaseApplication} instance.
     */
    public BaseApplication getBaseApplication(){
        return getBaseActivity().getBaseApplication();
    }


    /**
     * Shows a toast message.
     * @param message Toast message.
     */
    public void showToast(String message){
        getBaseActivity().showToast(message);
    }

    /**
     * Shows the loading view.
     */
    public void showLoading(){
        getBaseActivity().showLoading();
    }

    /**
     * Hides the loading view.
     */
    public void hideLoading(){
        getBaseActivity().hideLoading();
    }

}
