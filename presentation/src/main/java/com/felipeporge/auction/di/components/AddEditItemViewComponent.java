package com.felipeporge.auction.di.components;

import com.felipeporge.auction.di.interfaces.ActivityScope;
import com.felipeporge.auction.di.interfaces.DaggerComponent;
import com.felipeporge.auction.di.modules.views.AddEditItemModule;
import com.felipeporge.auction.presentation.views.activities.AddEditItemActivity;

import dagger.Subcomponent;

/**
 * This class represents the main view module.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 9/23/16
 */
@ActivityScope
@Subcomponent(modules = {AddEditItemModule.class})
public interface AddEditItemViewComponent extends DaggerComponent {

    void inject(AddEditItemActivity activity);
}
