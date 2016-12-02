package com.felipeporge.sample.presentation.presenters.main;

import com.felipeporge.sample.R;
import com.felipeporge.sample.domain.entities.SampleItem;
import com.felipeporge.sample.domain.interactors.base.UseCaseCallback;
import com.felipeporge.sample.domain.interactors.items.GetAllItemsUseCase;
import com.felipeporge.sample.presentation.mapper.ItemModelMapper;
import com.felipeporge.sample.presentation.model.SampleItemModel;
import com.felipeporge.sample.presentation.presenters.base.Presenter;
import com.felipeporge.sample.presentation.views.fragments.main.ItemListFragment;

import java.util.ArrayList;

/**
 * This class represents the item list view presenter.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 24/11/2016
 */
public class ItemListPresenter extends Presenter<ItemListFragment> {

    private ItemModelMapper mMapper;
    private GetAllItemsUseCase mGetAllItemsUc;

    /**
     * Constructor method.
     * @param getAllItemsUc - Get All Items Use Case.
     */
    public ItemListPresenter(ItemModelMapper mapper, GetAllItemsUseCase getAllItemsUc){
        mMapper = mapper;
        mGetAllItemsUc = getAllItemsUc;
    }

    @Override
    public void resume() {
        loadItems();
    }

    @Override
    public void pause() {}

    @Override
    public void destroy() {}

    /**
     * Loads auction items.
     */
    public void loadItems(){

        if(!isViewValid())
            return;

        getView().showLoading();
        mGetAllItemsUc.execute(null, new UseCaseCallback<ArrayList<SampleItem>>() {
            @Override
            public void onSuccess(ArrayList<SampleItem> domainItems) {
                ArrayList<SampleItemModel> items = mMapper.parseBack(domainItems);
                getView().hideLoading();
                if(items.size() > 0) {
                    getView().showItems(items);
                }else {
                    getView().showNoItems();
                }
            }

            @Override
            public void onFailure(Exception exception) {
                getView().hideLoading();
                getView().showToast(getViewContext().getString(R.string.item_list_failure));
            }
        });

    }
}
