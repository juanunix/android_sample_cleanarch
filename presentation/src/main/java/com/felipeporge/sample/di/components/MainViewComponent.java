package com.felipeporge.sample.di.components;

import com.felipeporge.sample.di.interfaces.ActivityScope;
import com.felipeporge.sample.di.interfaces.DaggerComponent;
import com.felipeporge.sample.di.modules.views.MainModule;
import com.felipeporge.sample.presentation.views.activities.MainActivity;
import com.felipeporge.sample.presentation.views.fragments.main.ItemListFragment;

import dagger.Subcomponent;

/**
 * This class represents the main view module.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 9/23/16
 */
@ActivityScope
@Subcomponent(modules = {MainModule.class})
public interface MainViewComponent extends DaggerComponent {

    void inject(MainActivity mainActivity);

    void inject(ItemListFragment itemListFragment);
}
