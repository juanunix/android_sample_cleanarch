package com.felipeporge.sample.presentation.presenters.base;

import android.content.Context;

import com.felipeporge.sample.presentation.views.PresenterView;

/**
 * This class represents a base presenter.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public abstract class Presenter<V extends PresenterView> {

    private V mView;

    /**
     * Resumes this presenter.
     */
    public abstract void resume();

    /**
     * Pauses this presenter.
     */
    public abstract void pause();

    /**
     * Destroys this presenter.
     */
    public abstract void destroy();

    /**
     * Gets presenter view.
     * @return {@link PresenterView} instance.
     */
    public V getView(){
        return mView;
    }

    /**
     * Sets presenter view.
     * @return {@link PresenterView} instance.
     */
    public void setView(V view){
        mView = view;
    }

    /**
     * Returns if the view is valid.
     * @return True (if the view is valid) or false (otherwise).
     */
    public boolean isViewValid(){
        return getView() != null && getView().isValid();
    }

    /**
     * Gets view context.
     * @return View context.
     */
    public Context getViewContext(){
        return getView().getViewContext();
    }
}
