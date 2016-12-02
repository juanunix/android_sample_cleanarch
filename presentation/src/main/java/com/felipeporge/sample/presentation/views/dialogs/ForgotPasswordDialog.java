package com.felipeporge.sample.presentation.views.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.felipeporge.sample.R;
import com.felipeporge.sample.presentation.presenters.LoginViewPresenter;
import com.felipeporge.sample.presentation.views.dialogs.base.OnDialogResultListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * This class represents a forgot password dialog.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public class ForgotPasswordDialog extends DialogFragment {

    /* ButterKnife View Injections - BEGIN */

    @BindView(R.id.forgot_password_birthday_tv)
    TextView birthdayTv;

    @BindView(R.id.forgot_password_doc_code_et)
    EditText docCodeEt;

    @BindView(R.id.forgot_password_email_et)
    EditText emailEt;

    @BindView(R.id.forgot_password_new_pass_et)
    EditText newPasswordEt;

    @BindView(R.id.forgot_password_new_pass_confirm_et)
    EditText newPasswordConfirmEt;

    /* ButterKnife View Injections - END */

    private String mBirthday = null;
    private BirthdayPickerDialog mBirthdayPickerDialog;
    private LoginViewPresenter mPresenter;

    /**
     * Constructor method.
     */
    public ForgotPasswordDialog() {}

    /**
     * Gets a new instance of {@link ForgotPasswordDialog}.
     * @return - {@link ForgotPasswordDialog} instance.
     */
    public static ForgotPasswordDialog getInstance(LoginViewPresenter loginPresenter) {

        ForgotPasswordDialog result = new ForgotPasswordDialog();
        result.mPresenter = loginPresenter;
        return result;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_forgot_password, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        mBirthdayPickerDialog = new BirthdayPickerDialog(getContext(), new OnDialogResultListener<String>() {
            @Override
            public void onResult(String result) {
                mBirthday = result;
                birthdayTv.setText(result);
            }
        });
    }

    @OnClick(R.id.forgot_password_birthday_tv)
    public void onSelectBirthdayTvClicked(){
        mBirthdayPickerDialog.show();
    }

    // NOTE: This listener is injected by ButterKnife.
    @OnClick(R.id.forgot_password_change_pass_bt)
    public void onChangePasswordBtClicked(){
        String email = emailEt.getText().toString();
        String doc = docCodeEt.getText().toString();
        String newPassword = newPasswordEt.getText().toString();
        String newPasswordConfirm = newPasswordConfirmEt.getText().toString();
        mPresenter.onChangePassword(email, doc, mBirthday, newPassword, newPasswordConfirm);
    }

}
