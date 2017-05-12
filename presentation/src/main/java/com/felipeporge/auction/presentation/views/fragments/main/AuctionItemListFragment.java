package com.felipeporge.auction.presentation.views.fragments.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.felipeporge.auction.R;
import com.felipeporge.auction.presentation.model.AuctionItemModel;
import com.felipeporge.auction.presentation.navigation.Navigator;
import com.felipeporge.auction.presentation.presenters.main.AuctionItemListPresenter;
import com.felipeporge.auction.presentation.views.activities.MainActivity;
import com.felipeporge.auction.presentation.views.adapters.AuctionItemRvAdapter;
import com.felipeporge.auction.presentation.views.adapters.base.OnRecyclerViewItemClicked;
import com.felipeporge.auction.presentation.views.fragments.base.PresenterFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * This class represents the item list fragment that shows all auction items.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public class AuctionItemListFragment extends PresenterFragment<MainActivity, AuctionItemListPresenter> implements OnRecyclerViewItemClicked<AuctionItemModel> {

    /* ButterKnife View Injections - BEGIN */

    @BindView(R.id.auction_item_list_recycler_view)
    RecyclerView itemListRv;

    @BindView(R.id.auction_item_list_loading_ll)
    LinearLayout loadingLl;

    @BindView(R.id.auction_item_list_no_items_ll)
    LinearLayout noItemsLl;

    /* ButterKnife View Injections - END */

    private AuctionItemRvAdapter mRvAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_auction_item_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle(R.string.auction_items_title);

        getBaseActivity().getComponent().inject(this);

        itemListRv.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onStart() {
        super.onStart();

        mRvAdapter = new AuctionItemRvAdapter(getContext(), getLoggedUserId());
        mRvAdapter.setOnItemClickListener(this);
        itemListRv.setAdapter(mRvAdapter);
    }

    // NOTE: This listener is injected by ButterKnife.
    @OnClick(R.id.auction_item_list_add_fab)
    public void onAddFabClicked(){
        Navigator.navigateToAddAnItem(getContext(), getBaseActivity().getLoggedUser().getId());
    }

    @Override
    public void showLoading() {
        itemListRv.setVisibility(View.GONE);
        noItemsLl.setVisibility(View.GONE);
        loadingLl.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingLl.setVisibility(View.GONE);
    }

    /**
     * Shows no items layout.
     */
    public void showNoItems(){
        noItemsLl.setVisibility(View.VISIBLE);
        itemListRv.setVisibility(View.GONE);
    }

    /**
     * Hides no items layout.
     */
    public void hideNoItems(){
        noItemsLl.setVisibility(View.GONE);
    }

    /**
     * Displays auction items.
     * @param items Items to display.
     */
    public void showItems(ArrayList<AuctionItemModel> items){
        itemListRv.setVisibility(View.VISIBLE);
        noItemsLl.setVisibility(View.GONE);

        Log.w(AuctionItemListFragment.class.getSimpleName(), "Item count: " + items);
        mRvAdapter.setData(items);
    }

    /**
     * Gets logged user id.
     * @return - Logged user id.
     */
    public long getLoggedUserId(){
        return getBaseActivity().getLoggedUser().getId();
    }

    @Override
    public void onItemClicked(int position, AuctionItemModel item) {
        long loggedUserId = getBaseActivity().getLoggedUser().getId();
        if(item.getUserId() != loggedUserId) {
            Navigator.navigateToItemDetails(getBaseActivity(), loggedUserId, item);
        }else{
            Navigator.navigateToEditAnItem(getBaseActivity(), item);
        }
    }
}
