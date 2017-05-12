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
import com.felipeporge.auction.presentation.presenters.main.BiddingItemListPresenter;
import com.felipeporge.auction.presentation.views.activities.MainActivity;
import com.felipeporge.auction.presentation.views.adapters.AuctionItemRvAdapter;
import com.felipeporge.auction.presentation.views.adapters.base.OnRecyclerViewItemClicked;
import com.felipeporge.auction.presentation.views.fragments.base.PresenterFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * This class represents the bidding item list fragment that shows all auction items in which the
 * logged user bid.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public class BiddingItemListFragment extends PresenterFragment<MainActivity, BiddingItemListPresenter>
        implements OnRecyclerViewItemClicked<AuctionItemModel> {

    /* ButterKnife View Injections - BEGIN */

    @BindView(R.id.bidding_items_recycler_view)
    RecyclerView itemListRv;

    @BindView(R.id.bidding_loading_ll)
    LinearLayout loadingLl;

    @BindView(R.id.bidding_no_items_ll)
    LinearLayout noItemsLl;

    /* ButterKnife View Injections - END */


    private AuctionItemRvAdapter mRvAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bidding_item_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle(R.string.bidding_title);

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
     * Displays bidding items.
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
     * @return Logged user id.
     */
    public Long getLoggedUserId() {
        return getBaseActivity().getLoggedUser().getId();
    }

    @Override
    public void onItemClicked(int position, AuctionItemModel item) {
        Navigator.navigateToItemDetails(getBaseActivity(), getBaseActivity().getLoggedUser().getId(), item);
    }
}
