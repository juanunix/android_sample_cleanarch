package com.felipeporge.auction.di.components;

import com.felipeporge.auction.di.interfaces.ActivityScope;
import com.felipeporge.auction.di.interfaces.DaggerComponent;
import com.felipeporge.auction.di.modules.views.ItemDetailsModule;
import com.felipeporge.auction.presentation.views.activities.ItemDetailsActivity;

import dagger.Subcomponent;

/**
 * This class represents the item details view component.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 24/11/2016
 */

@ActivityScope
@Subcomponent(modules = {ItemDetailsModule.class})
public interface ItemDetailsViewComponent extends DaggerComponent {

    void inject(ItemDetailsActivity activity);
}
