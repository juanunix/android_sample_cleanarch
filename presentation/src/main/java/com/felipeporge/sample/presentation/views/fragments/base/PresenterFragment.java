package com.felipeporge.sample.presentation.views.fragments.base;

import android.content.Context;

import com.felipeporge.sample.presentation.presenters.base.Presenter;
import com.felipeporge.sample.presentation.views.PresenterView;
import com.felipeporge.sample.presentation.views.activities.base.BaseActivity;

import javax.inject.Inject;

/**
 * This class represents a presenter fragment.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public class PresenterFragment<A extends BaseActivity, P extends Presenter>
        extends BaseFragment<A> implements PresenterView<P> {

    @Inject
    P presenter;

    @Override
    public void onStart() {
        super.onStart();

        if(presenter != null)
            presenter.setView(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        if(presenter != null)
            presenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();

        if(presenter != null)
            presenter.pause();
    }

    @Override
    public void onDestroy() {
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
        return !isDetached() && !isRemoving() && getBaseActivity() != null;
    }

    @Override
    public Context getViewContext() {
        return getBaseActivity();
    }
}
