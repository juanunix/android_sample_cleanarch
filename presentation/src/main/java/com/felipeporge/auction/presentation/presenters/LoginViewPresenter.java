package com.felipeporge.auction.presentation.presenters;

import android.util.Log;

import com.felipeporge.auction.R;
import com.felipeporge.auction.domain.entities.AuctionUser;
import com.felipeporge.auction.domain.interactors.base.UseCaseCallback;
import com.felipeporge.auction.domain.interactors.prefs.GetShowedWalkthroughUseCase;
import com.felipeporge.auction.domain.interactors.prefs.PutShowedWalkthroughUseCase;
import com.felipeporge.auction.domain.interactors.users.GetUserByCredentialsUseCase;
import com.felipeporge.auction.domain.interactors.users.GetUserByEmailUseCase;
import com.felipeporge.auction.domain.interactors.users.UpdateUserUseCase;
import com.felipeporge.auction.presentation.helpers.EncryptorHelper;
import com.felipeporge.auction.presentation.helpers.InfoValidatorHelper;
import com.felipeporge.auction.presentation.mapper.UserModelMapper;
import com.felipeporge.auction.presentation.model.UserModel;
import com.felipeporge.auction.presentation.presenters.base.Presenter;
import com.felipeporge.auction.presentation.views.activities.LoginActivity;

/**
 * This class represents the login view presenter.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public class LoginViewPresenter extends Presenter<LoginActivity> {

    private GetUserByCredentialsUseCase mGetUserByCredentialsUc;
    private GetUserByEmailUseCase mGetUserByEmailUc;
    private UpdateUserUseCase mUpdateUserUc;
    private GetShowedWalkthroughUseCase mGetShowedWalkthroughUseCase;
    private PutShowedWalkthroughUseCase mPutShowedWalkthroughUseCase;

    private UserModelMapper mMapper;

    /**
     * Constructor method.
     * @param mapper User model mapper.
     * @param getUserByCredentialsUc Use case to get an user by his credentials.
     */
    public LoginViewPresenter(UserModelMapper mapper, GetUserByCredentialsUseCase getUserByCredentialsUc,
                              GetUserByEmailUseCase getUserByEmailUseCase, UpdateUserUseCase updateUserUseCase,
                              GetShowedWalkthroughUseCase getShowedWalkthroughUseCase, PutShowedWalkthroughUseCase putShowedWalkthroughUseCase){
        mMapper = mapper;
        mGetUserByCredentialsUc = getUserByCredentialsUc;
        mGetUserByEmailUc = getUserByEmailUseCase;
        mUpdateUserUc = updateUserUseCase;
        mGetShowedWalkthroughUseCase = getShowedWalkthroughUseCase;
        mPutShowedWalkthroughUseCase = putShowedWalkthroughUseCase;
    }

    @Override
    public void resume() {
        mGetShowedWalkthroughUseCase.execute(null, new UseCaseCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean showedWalkthrough) {
                if(!showedWalkthrough && isViewValid()){
                    getView().showWalkthrough();
                }
            }

            @Override
            public void onFailure(Exception exception) {
                exception.printStackTrace();
            }
        });
    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    /**
     * On view showed walkthrough.
     */
    public void onShowedWalkthrough(){
        mPutShowedWalkthroughUseCase.execute(true, new UseCaseCallback<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.w(LoginViewPresenter.class.getSimpleName(), "Successfully showed walkthrough.");
            }

            @Override
            public void onFailure(Exception exception) {
                exception.printStackTrace();
            }
        });
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

        getView().showLoading();
        mGetUserByCredentialsUc.execute(email + ":" + EncryptorHelper.encrypt(password), new UseCaseCallback<AuctionUser>() {
            @Override
            public void onSuccess(AuctionUser auctionUser) {
                if(auctionUser != null) {
                    UserModel user = mMapper.parseBack(auctionUser);
                    String firstName = user.getName().split(" ")[0];

                    getView().hideLoading();
                    getView().showToast(getViewContext().getString(R.string.login_welcome, firstName));
                    getView().navigateToMainScreen(user);
                }else{
                    onFailure(new Exception());
                }
            }

            @Override
            public void onFailure(Exception exception) {
                    getView().hideLoading();
                    getView().showToast(getViewContext().getString(R.string.login_user_not_found));
            }
        });
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

        if(!InfoValidatorHelper.validateEmail(email)) {
            getView().showToast(getViewContext().getString(R.string.sign_up_invalid_email));
            return;
        }

        if(newPasswordConfirm == null || !newPasswordConfirm.equals(newPassword)){
            getView().showToast(getViewContext().getString(R.string.sign_up_invalid_confirm_password));
            return;
        }

        if(!InfoValidatorHelper.validatePassword(newPassword)) {
            getView().showToast(getViewContext().getString(R.string.sign_up_invalid_password));
            return;
        }

        if(doc == null || doc.length() == 0) {
            getView().showToast(getViewContext().getString(R.string.sign_up_invalid_document));
            return;
        }

        if(birthday == null && birthday.length() == 0) {
            getView().showToast(getViewContext().getString(R.string.sign_up_invalid_birthday));
            return;
        }

        getView().showLoading();
        mGetUserByEmailUc.execute(email, new UseCaseCallback<AuctionUser>() {
            @Override
            public void onSuccess(AuctionUser auctionUser) {
                UserModel user = mMapper.parseBack(auctionUser);
                if(user.getBirthday().equals(birthday) && user.getDocCode().equals(doc)){
                    user.setPassword(EncryptorHelper.encrypt(newPassword));
                    mUpdateUserUc.execute(mMapper.transform(user), new UseCaseCallback<AuctionUser>() {
                        @Override
                        public void onSuccess(AuctionUser auctionUser) {
                            getView().hideLoading();
                            getView().hideChangePasswordDialog();
                            getView().showToast(getViewContext().getString(R.string.login_change_password_success));
                        }

                        @Override
                        public void onFailure(Exception exception) {
                            getView().hideLoading();
                            getView().showToast(getViewContext().getString(R.string.login_change_password_failure));
                        }
                    });
                }
            }

            @Override
            public void onFailure(Exception exception) {
                getView().hideLoading();
                if(exception instanceof IllegalArgumentException){
                    getView().showToast(getViewContext().getString(R.string.login_change_password_invalid_data));
                }else{
                    getView().showToast(getViewContext().getString(R.string.login_change_password_failure));
                }
            }
        });

    }
}
