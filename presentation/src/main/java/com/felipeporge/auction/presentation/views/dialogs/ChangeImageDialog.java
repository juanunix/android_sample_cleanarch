package com.felipeporge.auction.presentation.views.dialogs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.felipeporge.auction.R;
import com.felipeporge.auction.presentation.views.dialogs.base.OnDialogResultListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * This class represents a change image dialog.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public class ChangeImageDialog extends DialogFragment {

    /* ButterKnife View Injections - BEGIN */

    @BindView(R.id.change_img_et)
    EditText imgEt;

    /* ButterKnife View Injections - END */

    private String mCurrUrl;
    private OnDialogResultListener<String> mListener;

    /**
     * Constructor method.
     */
    public ChangeImageDialog() {}

    public static ChangeImageDialog getInstance(String currUrl, OnDialogResultListener<String> listener) {

        ChangeImageDialog result = new ChangeImageDialog();
        result.mCurrUrl = currUrl;
        result.mListener = listener;
        return result;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_change_image, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        imgEt.setText(mCurrUrl);
    }

    // NOTE: This listener is injected by ButterKnife.
    @OnClick(R.id.change_img_save_bt)
    public void onSaveBtClicked(){
        if(mListener != null){
            mListener.onResult(imgEt.getText().toString());
        }
        dismiss();
    }

}
