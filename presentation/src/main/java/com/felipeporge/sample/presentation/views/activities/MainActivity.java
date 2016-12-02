package com.felipeporge.sample.presentation.views.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

import com.felipeporge.sample.R;
import com.felipeporge.sample.di.components.MainViewComponent;
import com.felipeporge.sample.di.interfaces.HasComponent;
import com.felipeporge.sample.di.modules.views.MainModule;
import com.felipeporge.sample.presentation.views.activities.base.BaseActivity;
import com.felipeporge.sample.presentation.views.fragments.main.ItemListFragment;

/**
 * This class represents the main activity.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public class MainActivity extends BaseActivity implements HasComponent<MainViewComponent>{

    /* ButterKnife View Injections - BEGIN */


    /* ButterKnife View Injections - END */

    private MainViewComponent mComponent;

    /**
     * Gets main calling intent.
     * @param context The context.
     * @return Main activity calling intent.
     */
    public static Intent getCallingIntent(Context context){
        Intent intent = new Intent(context, MainActivity.class);

        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getComponent().inject(this);

        attachFragment(R.id.main_content_fl, new ItemListFragment());
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

    @Override
    public MainViewComponent getComponent() {
        if (mComponent == null) {
            return getBaseApplication().getComponent().plus(new MainModule());
        }

        return mComponent;
    }
}
