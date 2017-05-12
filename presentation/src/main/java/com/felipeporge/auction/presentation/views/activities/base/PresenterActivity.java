package com.felipeporge.auction.presentation.views.activities.base;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.felipeporge.auction.presentation.presenters.base.Presenter;
import com.felipeporge.auction.presentation.views.PresenterView;

import javax.inject.Inject;


/**
 * This class represents a base {@link AppCompatActivity}.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public abstract class PresenterActivity<P extends Presenter> extends BaseActivity implements PresenterView<P> {

    /* Dagger Injections - BEGIN */

    @Inject
    public P presenter;

    /* Dagger Injections - END */

    @Override
    protected void onStart() {
        super.onStart();

        if(presenter != null)
            presenter.setView(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(presenter != null)
            presenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(presenter != null)
            presenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(presenter != null)
            presenter.destroy();
    }

    @Override
    public P getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(P presenter) {
        this.presenter = presenter;
    }

    @Override
    public boolean isValid() {
        return !isFinishing() && !isDestroyed();
    }

    @Override
    public Context getViewContext() {
        return this;
    }
}