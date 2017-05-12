package com.felipeporge.auction.presentation.views.dialogs;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.Toast;

import com.felipeporge.auction.R;
import com.felipeporge.auction.config.Constants;
import com.felipeporge.auction.presentation.views.dialogs.base.OnDialogResultListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * This class represents a birthday picker dialog.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public class BirthdayPickerDialog {

    private Context mContext;
    private OnDialogResultListener<String> mListener;
    private SimpleDateFormat mDateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    private DatePickerDialog mDatePicker;

    /**
     * Private constructor.
     */
    private BirthdayPickerDialog(){}

    /**
     * Constructor method.
     * @param context The context.
     * @param listener On dialog result listener.
     */
    public BirthdayPickerDialog(Context context, OnDialogResultListener<String> listener) {
        mContext = context;
        mListener = listener;

        Calendar tmp = Calendar.getInstance();
        int day = tmp.get(Calendar.DAY_OF_MONTH);
        int month = tmp.get(Calendar.MONTH);
        int year = tmp.get(Calendar.YEAR);

        mDatePicker = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                Calendar tmp = Calendar.getInstance();
                tmp.set(Calendar.DAY_OF_MONTH, selectedDay);
                tmp.set(Calendar.MONTH, selectedMonth);
                tmp.set(Calendar.YEAR, selectedYear);

                if((Calendar.getInstance().getTimeInMillis() - tmp.getTimeInMillis())/1000 < Constants.SignUp.MINIMUM_AGE_MILLIS){
                    Toast.makeText(mContext, mContext.getString(R.string.birthday_picker_invalid_birthday), Toast.LENGTH_LONG).show();
                }else{
                    if(mListener != null) {
                        mListener.onResult(mDateFormatter.format(tmp.getTime()));
                    }
                    BirthdayPickerDialog.this.dismiss();
                }
            }
        }, year, month, day);
    }

    /**
     * Shows this dialog.
     */
    public void show(){
        if(!mDatePicker.isShowing()){
            mDatePicker.show();
        }
    }

    /**
     * Dismisses this dialog.
     */
    public void dismiss(){
        if(mDatePicker.isShowing()){
            mDatePicker.dismiss();
        }
    }

}
