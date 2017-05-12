package com.felipeporge.auction.presentation.presenters;

import android.util.Log;

import com.felipeporge.auction.R;
import com.felipeporge.auction.domain.entities.AuctionUser;
import com.felipeporge.auction.domain.interactors.base.UseCaseCallback;
import com.felipeporge.auction.domain.interactors.users.CreateUserUseCase;
import com.felipeporge.auction.domain.interactors.users.GetUserByEmailUseCase;
import com.felipeporge.auction.presentation.helpers.EncryptorHelper;
import com.felipeporge.auction.presentation.helpers.InfoValidatorHelper;
import com.felipeporge.auction.presentation.mapper.UserModelMapper;
import com.felipeporge.auction.presentation.model.UserModel;
import com.felipeporge.auction.presentation.presenters.base.Presenter;
import com.felipeporge.auction.presentation.views.activities.SignUpActivity;

/**
 * This class represents the sign up view presenter.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public class SignUpViewPresenter extends Presenter<SignUpActivity> {

    private CreateUserUseCase mCreateUserUc;
    private GetUserByEmailUseCase mGetUserByEmailUc;
    private UserModelMapper mMapper;

    /**
     * Constructor method.
     * @param mapper User model mapper.
     * @param createUserUc Use case to create an user.
     * @param getUserUc Use case to get an user by email.
     */
    public SignUpViewPresenter(UserModelMapper mapper, CreateUserUseCase createUserUc, GetUserByEmailUseCase getUserUc){
        mMapper = mapper;
        mCreateUserUc = createUserUc;
        mGetUserByEmailUc = getUserUc;
    }

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
     * * Called when the user sends a new user.
     * @param name User's full name.
     * @param documentCode User's document code.
     * @param email User's email.
     * @param password User's password.
     * @param confirmPassword User's password confirmation
     * @param birthday User's birthday.
     */
    public void onSendUser(String name, String documentCode, String email,
                           String password, String confirmPassword, String birthday){

        if(!isViewValid()) {
            Log.w(SignUpViewPresenter.class.getSimpleName(), "Invalid view.");
            return;
        }


        final UserModel user = new UserModel();
        if(InfoValidatorHelper.validateFullName(name)) {
            user.setName(name);
        }else{
            getView().showToast(getViewContext().getString(R.string.sign_up_invalid_name));
            return;
        }

        if(InfoValidatorHelper.validateEmail(email)) {
            user.setEmail(email);
        }else{
            getView().showToast(getViewContext().getString(R.string.sign_up_invalid_email));
            return;
        }

        if(confirmPassword == null || !confirmPassword.equals(password)){
            getView().showToast(getViewContext().getString(R.string.sign_up_invalid_confirm_password));
            return;
        }

        if(InfoValidatorHelper.validatePassword(password)) {
            user.setPassword(EncryptorHelper.encrypt(password));
        }else{
            getView().showToast(getViewContext().getString(R.string.sign_up_invalid_password));
            return;
        }

        if(documentCode != null && documentCode.length() > 0) {
            user.setDocCode(documentCode);
        }else{
            getView().showToast(getViewContext().getString(R.string.sign_up_invalid_document));
            return;
        }

        if(birthday != null && birthday.length() > 0) {
            user.setBirthday(birthday);
        }else {
            getView().showToast(getViewContext().getString(R.string.sign_up_invalid_birthday));
            return;
        }

        getView().showLoading();
        mGetUserByEmailUc.execute(email, new UseCaseCallback<AuctionUser>() {
            @Override
            public void onSuccess(AuctionUser auctionUser) {
                getView().hideLoading();
                getView().showToast(getViewContext().getString(R.string.sign_up_email_already_registered));
            }

            @Override
            public void onFailure(Exception exception) {
                if(exception instanceof IllegalArgumentException){
                    continueToSignUp(user);
                }else {
                    getView().hideLoading();
                    getView().showToast(getViewContext().getString(R.string.sign_up_email_validation_failure));
                }
            }
        });
    }

    /**
     * Continues to sign up.
     * @param user User model.
     */
    private void continueToSignUp(UserModel user){
        mCreateUserUc.execute(mMapper.transform(user), new UseCaseCallback<AuctionUser>() {
            @Override
            public void onSuccess(AuctionUser auctionUser) {
                UserModel user = mMapper.parseBack(auctionUser);
                String firstName = user.getName().split(" ")[0];

                getView().hideLoading();
                getView().showToast(getViewContext().getString(R.string.sign_up_success, firstName));
                getView().navigateToMainScreen(user);
            }

            @Override
            public void onFailure(Exception exception) {
                getView().hideLoading();
                getView().showToast(getViewContext().getString(R.string.sign_up_failed));
            }
        });
    }
}
