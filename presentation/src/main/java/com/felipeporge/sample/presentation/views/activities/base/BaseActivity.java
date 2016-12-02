package com.felipeporge.sample.presentation.views.activities.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.felipeporge.sample.BaseApplication;
import com.felipeporge.sample.R;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;

import butterknife.ButterKnife;

/**
 * This class represents a base {@link AppCompatActivity}.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public class BaseActivity extends AppCompatActivity {

    private ProgressDialog mPDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPDialog = new ProgressDialog(this);
        mPDialog.setIndeterminate(true);
        mPDialog.setCancelable(false);
        mPDialog.setTitle(null);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this, getBaseView());

        if(!Fresco.hasBeenInitialized())
            Fresco.initialize(this);
    }

    /**
     * This method attaches a tab.
     * @param containerViewId - Container view.
     * @param fragment - Fragment instance to attach.
     */
    public boolean attachFragment(int containerViewId, Fragment fragment){
        return attachFragment(containerViewId, fragment, fragment.getClass().getSimpleName());
    }


    /**
     * This method attaches a tab.
     * @param containerViewId - Container view.
     * @param fragment - Fragment instance to attach.
     * @param tag - Fragment tag.
     */
    public boolean attachFragment(int containerViewId, Fragment fragment, String tag) {
        if (findViewById(containerViewId) != null) {

            List<Fragment> fragments = getSupportFragmentManager().getFragments();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            if (!fragment.isAdded() || fragment.isDetached()) { // The fragment exists, so we need to reattach it.
//            transaction.addToBackStack(tag);
                if(fragments == null || fragments.isEmpty()) {
                    transaction.add(containerViewId, fragment, tag).commit();
                } else {
                    transaction.replace(containerViewId, fragment, tag).commit();
                }
                return true;
            }
            //else{ // This fragment is currently attached, so we don't need to reattach.

            //}
        }
        return false;
    }

    /**
     * Shows a dialog.
     * @param dialog - {@link DialogFragment} instance.
     */
    public void showDialog(DialogFragment dialog){
        dialog.show(getSupportFragmentManager(), dialog.getClass().getSimpleName());
    }

    /**
     * Shows a toast message.
     * @param message Toast message.
     */
    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Finds a {@link Fragment} using its tag.
     * @param tag - {@link String} tag.
     * @return {@link Fragment} found.
     */
    public Fragment findFragmentByTag(String tag){
        return getSupportFragmentManager().findFragmentByTag(tag);
    }

    /**
     * Gets base activity {@link View}.
     * @return {@link View} instance.
     */
    public View getBaseView(){
        return getWindow().getDecorView();
    }

    /**
     * Gets the base application.
     * @return {@link BaseApplication} instance.
     */
    public BaseApplication getBaseApplication(){
        return (BaseApplication) getApplication();
    }

    /**
     * Shows the loading view.
     * @param message Message to show.
     */
    public void showLoading(String message){
        mPDialog.setMessage(message);

        if(!mPDialog.isShowing())
            mPDialog.show();
    }

    /**
     * Shows the loading view.
     */
    public void showLoading(){
        mPDialog.setMessage(getString(R.string.general_loading));

        if(!mPDialog.isShowing())
            mPDialog.show();
    }

    /**
     * Hides the loading view.
     */
    public void hideLoading(){
        if(mPDialog.isShowing())
            mPDialog.dismiss();
    }
}