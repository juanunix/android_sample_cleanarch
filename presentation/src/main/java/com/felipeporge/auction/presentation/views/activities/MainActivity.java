package com.felipeporge.auction.presentation.views.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;

import com.felipeporge.auction.R;
import com.felipeporge.auction.di.components.MainViewComponent;
import com.felipeporge.auction.di.interfaces.HasComponent;
import com.felipeporge.auction.di.modules.views.MainModule;
import com.felipeporge.auction.presentation.model.UserModel;
import com.felipeporge.auction.presentation.navigation.Navigator;
import com.felipeporge.auction.presentation.views.activities.base.BaseActivity;
import com.felipeporge.auction.presentation.views.fragments.main.AuctionItemListFragment;
import com.felipeporge.auction.presentation.views.fragments.main.BiddingItemListFragment;
import com.felipeporge.auction.presentation.views.fragments.main.StoreFragment;
import com.felipeporge.auction.presentation.views.fragments.main.WonItemListFragment;

import butterknife.BindView;

/**
 * This class represents the main activity.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public class MainActivity extends BaseActivity implements HasComponent<MainViewComponent>{

    private static final String USER_EXTRA_KEY = "usr";

    /* ButterKnife View Injections - BEGIN */

    @BindView(R.id.main_bottom_navigation)
    BottomNavigationView bottomNavView;

    /* ButterKnife View Injections - END */

    private UserModel mLoggedUser;
    private MainViewComponent mComponent;

    /**
     * Gets main calling intent.
     * @param context The context.
     * @param user User model.
     * @return Main activity calling intent.
     */
    public static Intent getCallingIntent(Context context, UserModel user){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(USER_EXTRA_KEY, user);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getComponent().inject(this);

        Bundle extras = getIntent().getExtras();
        boolean isLogged = false;
        if(extras != null){
            mLoggedUser = (UserModel) extras.get(USER_EXTRA_KEY);
            if(mLoggedUser != null){
                isLogged = true;
            }
        }

        if(!isLogged){
            Navigator.navigateToLoginScreen(this, true);
            showToast(getString(R.string.main_login_error));
            finish();
        }

        bottomNavView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        return attachTab(item.getItemId());
                    }
                }
        );

        attachTab(R.id.action_items);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.main_action_logout:
                finish();
                return true;
        }
        return false;
    }


    /**
     * Attaches a new tab.
     * @param tabId Tab id.
     * @return True (if the tab was attached) or false (otherwise).
     */
    private boolean attachTab(int tabId){
        switch (tabId){
            default:
            case R.id.action_items:
                attachFragment(R.id.main_content_fl, new AuctionItemListFragment());
                break;

            case R.id.action_bids:
                attachFragment(R.id.main_content_fl, new BiddingItemListFragment());
                break;

            case R.id.action_won:
                attachFragment(R.id.main_content_fl, new WonItemListFragment());
                break;

            case R.id.action_store:
                attachFragment(R.id.main_content_fl, new StoreFragment());
                break;

        }
        return false;
    }

    /**
     * Gets logged user.
     * @return {@link UserModel} instance.
     */
    public UserModel getLoggedUser(){
        return mLoggedUser;
    }

    @Override
    public MainViewComponent getComponent() {
        if (mComponent == null) {
            return getBaseApplication().getComponent().plus(new MainModule());
        }

        return mComponent;
    }
}
