package com.felipeporge.sample.presentation.views.dialogs.base;

/**
 * This class represents the dialog result listener.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public interface OnDialogResultListener<T> {

    /**
     * Handles when the dialog is finishing.
     * @param result Result.
     */
    void onResult(T result);
}
