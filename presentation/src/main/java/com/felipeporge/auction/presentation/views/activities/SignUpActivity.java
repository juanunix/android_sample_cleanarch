package com.felipeporge.auction.presentation.views.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.TextView;

import com.felipeporge.auction.BaseApplication;
import com.felipeporge.auction.R;
import com.felipeporge.auction.di.components.SignUpViewComponent;
import com.felipeporge.auction.di.interfaces.HasComponent;
import com.felipeporge.auction.di.modules.views.SignUpModule;
import com.felipeporge.auction.presentation.model.UserModel;
import com.felipeporge.auction.presentation.navigation.Navigator;
import com.felipeporge.auction.presentation.presenters.SignUpViewPresenter;
import com.felipeporge.auction.presentation.views.activities.base.PresenterActivity;
import com.felipeporge.auction.presentation.views.dialogs.BirthdayPickerDialog;
import com.felipeporge.auction.presentation.views.dialogs.base.OnDialogResultListener;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * This class represents a registration form activity.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public class SignUpActivity extends PresenterActivity<SignUpViewPresenter> implements HasComponent<SignUpViewComponent> {

    /* ButterKnife View Injections - BEGIN */

    @BindView(R.id.sign_up_full_name_et)
    EditText fullNameEt;

    @BindView(R.id.sign_up_doc_code_et)
    EditText docCodeEt;

    @BindView(R.id.sign_up_email_et)
    EditText emailEt;

    @BindView(R.id.sign_up_pass_et)
    EditText passwordEt;

    @BindView(R.id.sign_up_pass_confirm_et)
    EditText passwordConfirmationEt;

    @BindView(R.id.sign_up_birthday_tv)
    TextView birthdayTv;

    /* ButterKnife View Injections - END */

    private BirthdayPickerDialog mBirthdayPickerDialog;
    private String mBirthday = null;
    private SignUpViewComponent mComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Dependencies Injection:
        mComponent = getComponent();
        mComponent.inject(this);

        mBirthdayPickerDialog = new BirthdayPickerDialog(this, new OnDialogResultListener<String>() {
            @Override
            public void onResult(String result) {
                mBirthday = result;
                birthdayTv.setText(mBirthday);
            }
        });
    }

    // NOTE: This listener is injected by ButterKnife.
    @OnClick(R.id.sign_up_birthday_tv)
    public void onBirthdayTvClicked(){
        mBirthdayPickerDialog.show();
    }

    // NOTE: This listener is injected by ButterKnife.
    @OnClick(R.id.sign_up_send_bt)
    public void onSendBtClicked(){
        String name = fullNameEt.getText().toString();
        String documentCode = docCodeEt.getText().toString();
        String email = emailEt.getText().toString();
        String password = passwordEt.getText().toString();
        String confirmPassword = passwordConfirmationEt.getText().toString();
        String birthday = null;

        if(mBirthday != null){
            birthday = birthdayTv.getText().toString();
        }

        getPresenter().onSendUser(name, documentCode, email, password, confirmPassword, birthday);
    }

    /**
     * Goes to the main screen.
     */
    public void navigateToMainScreen(UserModel user){
        Navigator.navigateToMainScreen(this, user);
        finish();
    }

    @Override
    public SignUpViewComponent getComponent() {
        if (mComponent == null) {
            mComponent = BaseApplication.getComponent(this).plus(new SignUpModule());
        }

        return mComponent;
    }
}
