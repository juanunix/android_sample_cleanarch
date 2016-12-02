package com.felipeporge.sample.presentation.views.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.felipeporge.sample.R;
import com.felipeporge.sample.config.Constants;
import com.felipeporge.sample.presentation.helpers.UnitHelper;
import com.felipeporge.sample.presentation.navigation.Navigator;
import com.felipeporge.sample.presentation.views.activities.base.BaseActivity;

import butterknife.BindView;

/**
 * This class represents the Splash screen.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public class SplashActivity extends BaseActivity {

    /* ButterKnife View Injections - BEGIN */

    @BindView(R.id.splash_app_icon_iv)
    ImageView logoIv;

    /* ButterKnife View Injections - END */

    private Handler mHandler = new Handler();
    private Runnable mNextScreenRunnable = new Runnable() {
        @Override
        public void run() {
            nextScreen();
        }
    };

    private int mEntranceTranslationY = 0;
    private boolean mGoNext = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        logoIv.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                logoIv.getViewTreeObserver().removeOnPreDrawListener(this);
                mEntranceTranslationY = (int) (logoIv.getTop() - UnitHelper.parsePixel(48, SplashActivity.this));

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        prepareTransaction();
                    }
                });
                return true;
            }
        });
    }

    /**
     * Prepares screen transaction.
     */
    private void prepareTransaction(){
        animateEntrance(logoIv);
        mHandler.postDelayed(mNextScreenRunnable, Constants.Splash.SPLASH_DELAY_MS);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(mGoNext){
            nextScreen();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        mGoNext = true;
        mHandler.removeCallbacks(mNextScreenRunnable);
    }

    /**
     * Animates a view.
     * @param view {@link View} to animateEntrance.
     */
    private void animateEntrance(View view){
        ViewCompat.animate(view)
                .translationY(-mEntranceTranslationY)
                .setDuration(Constants.Splash.SPLASH_DELAY_MS)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .start();
    }

    /**
     * Goes to the next screen.
     */
    private void nextScreen(){
//        Navigator.navigateToMainScreen(this);
        Navigator.navigateToLoginScreen(this, false);
        overridePendingTransition(0, 0);
        finish();
    }
}