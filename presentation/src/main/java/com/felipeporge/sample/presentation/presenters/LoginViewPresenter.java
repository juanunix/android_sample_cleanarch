package com.felipeporge.sample.presentation.presenters;

import android.util.Log;

import com.felipeporge.sample.R;
import com.felipeporge.sample.presentation.helpers.InfoValidatorHelper;
import com.felipeporge.sample.presentation.presenters.base.Presenter;
import com.felipeporge.sample.presentation.views.activities.LoginActivity;

/**
 * This class represents the login view presenter.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public class LoginViewPresenter extends Presenter<LoginActivity> {


    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    /**
     * * Called when the user logs in.
     * @param email User's email.
     * @param password User's password.
     */
    public void onLogin(String email, String password){

        if(!isViewValid()) {
            Log.w(LoginViewPresenter.class.getSimpleName(), "Invalid view.");
            return;
        }

        if(!InfoValidatorHelper.validateEmail(email)) {
            getView().showToast(getViewContext().getString(R.string.login_invalid_email));
            return;
        }

        if(!InfoValidatorHelper.validatePassword(password)) {
            getView().showToast(getViewContext().getString(R.string.login_invalid_password));
            return;
        }

        getView().navigateToMainScreen();
    }


    /**
     * On user changing password.
     * @param email User's registered e-mail.
     * @param doc User's document code/number.
     * @param birthday User's birthday.
     * @param newPassword The new password.
     * @param newPasswordConfirm The new password confirmation.
     */
    public void onChangePassword(String email, final String doc, final String birthday, final String newPassword, String newPasswordConfirm){

        // TODO: Validate all data using {@link InfoValidatorHelper}.

        getView().hideChangePasswordDialog();
        getView().showToast(getViewContext().getString(R.string.login_change_password_success));
    }
}
