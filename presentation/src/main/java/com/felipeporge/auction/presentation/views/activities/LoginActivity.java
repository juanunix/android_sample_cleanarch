package com.felipeporge.auction.presentation.views.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.felipeporge.auction.BaseApplication;
import com.felipeporge.auction.R;
import com.felipeporge.auction.config.Constants;
import com.felipeporge.auction.di.components.LoginViewComponent;
import com.felipeporge.auction.di.interfaces.HasComponent;
import com.felipeporge.auction.di.modules.views.LoginModule;
import com.felipeporge.auction.presentation.model.UserModel;
import com.felipeporge.auction.presentation.navigation.Navigator;
import com.felipeporge.auction.presentation.presenters.LoginViewPresenter;
import com.felipeporge.auction.presentation.views.activities.base.PresenterActivity;
import com.felipeporge.auction.presentation.views.dialogs.ForgotPasswordDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * This class represents the login screen.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public class LoginActivity extends PresenterActivity<LoginViewPresenter> implements HasComponent<LoginViewComponent> {

    /* ButterKnife View Injections - BEGIN */

    @BindView(R.id.login_fields_ll)
    LinearLayout fieldsLl;

    @BindView(R.id.login_links_ll)
    LinearLayout linksLl;

    @BindView(R.id.login_email_et)
    EditText emailEt;

    @BindView(R.id.login_pass_et)
    EditText passEt;

    /* ButterKnife View Injections - END */

    private LoginViewComponent mComponent;
    private ForgotPasswordDialog mForgotPasswordDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Dependencies Injection:
        mComponent = getComponent();
        mComponent.inject(this);

        mForgotPasswordDialog = ForgotPasswordDialog.getInstance(getPresenter());
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Reset alpha to animate.
        prepareToAnimate();

        animateEntrance(fieldsLl);
        animateEntrance(linksLl);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Reset alpha to animate.
        prepareToAnimate();
    }


    @Override
    public LoginViewComponent getComponent() {
        if (mComponent == null) {
            mComponent = BaseApplication.getComponent(this).plus(new LoginModule());
        }

        return mComponent;
    }

    /**
     * Prepares the view to animate.
     */
    private void prepareToAnimate(){
        fieldsLl.setAlpha(0);
        linksLl.setAlpha(0);
    }

    /**
     * Animates a view.
     * @param view {@link View} to animateEntrance.
     */
    private void animateEntrance(View view){
        ViewCompat.animate(view)
                .alpha(1)
                .setDuration(Constants.Login.LOGIN_ENTRANCE_DELAY)
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .start();
    }

    // NOTE: This listener is injected by ButterKnife.
    @OnClick(R.id.login_sign_in_bt)
    public void onSignInBtClicked(){
        getPresenter().onLogin(emailEt.getText().toString(), passEt.getText().toString());
    }

    // NOTE: This listener is injected by ButterKnife.
    @OnClick(R.id.login_forgot_pass_tv)
    public void onForgotPassTvClicked(){
        showDialog(mForgotPasswordDialog);
    }

    // NOTE: This listener is injected by ButterKnife.
    @OnClick(R.id.login_sign_up_tv)
    public void onSignUpTvClicked(){
        Navigator.navigateToSignUp(this);
    }

    /**
     * Goest to the main screen.
     */
    public void navigateToMainScreen(UserModel user){
        Navigator.navigateToMainScreen(this, user);
    }

    /**
     * Shows the walkthrough.
     */
    public void showWalkthrough(){
        if(Navigator.navigateToWalkthrough(this)){
            getPresenter().onShowedWalkthrough();
        }
    }

    /**
     * Hides the forgot password dialog.
     */
    public void hideChangePasswordDialog(){
        mForgotPasswordDialog.dismiss();
    }
}
