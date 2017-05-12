package com.felipeporge.auction.presentation.views.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.felipeporge.auction.R;
import com.felipeporge.auction.presentation.helpers.TimeHelper;
import com.felipeporge.auction.presentation.model.AuctionBidModel;
import com.felipeporge.auction.presentation.model.AuctionItemModel;
import com.felipeporge.auction.presentation.views.adapters.base.OnRecyclerViewItemClicked;

import java.text.DecimalFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * This class represents an auction item recyclerview adapter.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public class AuctionItemRvAdapter extends RecyclerView.Adapter<AuctionItemRvAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<AuctionItemModel> mData = new ArrayList<>();
    private DecimalFormat mPriceFormatter = new DecimalFormat("'$'0.00");

    private OnRecyclerViewItemClicked<AuctionItemModel> mListener;
    private long mLoggedUserId;

    /**
     * Constructor method.
     */
    public AuctionItemRvAdapter(Context context, long loggedUserId){
        mContext = context;
        mLoggedUserId = loggedUserId;
    }

    /**
     * Constructor method.
     */
    public AuctionItemRvAdapter(Context context, ArrayList<AuctionItemModel> data, long loggedUserId){
        mData = data;
        mContext = context;
        mLoggedUserId = loggedUserId;
    }

    /**
     * Sets on item click listener.
     * @param listener - Listener instance.
     */
    public void setOnItemClickListener(OnRecyclerViewItemClicked<AuctionItemModel> listener){
        mListener = listener;
    }

    @Override
    public AuctionItemRvAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.row_auction_item, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(final AuctionItemRvAdapter.ViewHolder holder, int position) {
        AuctionItemModel item = mData.get(position);

        holder.itemTitleTv.setText(item.getTitle());
        holder.priceTv.setText(mPriceFormatter.format(item.getCurrentBidValue()));

        int bidCount = item.getBidCount();
        if(bidCount == 1) {
            holder.itemBidsTv.setText(mContext.getString(R.string.row_auction_item_bid));
        } else {
            holder.itemBidsTv.setText(mContext.getString(R.string.row_auction_item_bids, bidCount));
        }


        AuctionBidModel currBid = item.getCurrentBid();
        Log.w("RvAdapter", "currBid = " + currBid + "  loggedUserId = " + mLoggedUserId);
        if(currBid != null)
            holder.setIsUserWinning(currBid.getUserId() == mLoggedUserId);
        else
            holder.setIsUserWinning(false);

        long millisLeft = item.getEndDateMillis() - System.currentTimeMillis();
        if(millisLeft < 0){
            if(item.getBidCount() == 0)
                holder.timeLeftTv.setText(mContext.getString(R.string.row_auction_finished));
            else
                holder.timeLeftTv.setText(mContext.getString(R.string.row_auction_sold));
            holder.timeLeftTv.setTextColor(ContextCompat.getColor(mContext, R.color.red_800));
        }else{
            holder.timeLeftTv.setText(TimeHelper.formatMillis(millisLeft));
            holder.timeLeftTv.setTextColor(ContextCompat.getColor(mContext, R.color.grey_600));
        }

        try {
            holder.imageSdv.setImageURI(Uri.parse(item.getImg()));
        }catch(Exception e){
            Log.w(AuctionItemRvAdapter.class.getSimpleName(), "Invalid image url.");
        }

        holder.rootView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(mListener != null){
                    mListener.onItemClicked(holder.getAdapterPosition(), getItem(holder.getAdapterPosition()));
                }
            }
        });
    }

    /**
     * Sets a new data set.
     * @param data Data to set.
     */
    public void setData(ArrayList<AuctionItemModel> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    /**
     * Gets an item.
     * @param position Item position.
     * @return {@link AuctionItemModel}.
     */
    public AuctionItemModel getItem(int position){
        return mData.get(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * Defines an auction item view holder.
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        /* ButterKnife View Injection - BEGIN */

        @BindView(R.id.row_auction_item_img_sdv)
        SimpleDraweeView imageSdv;

        @BindView(R.id.row_auction_item_price_tv)
        TextView priceTv;

        @BindView(R.id.row_auction_item_time_left_tv)
        TextView timeLeftTv;

        @BindView(R.id.row_auction_item_title_tv)
        TextView itemTitleTv;

        @BindView(R.id.row_auction_item_bids_tv)
        TextView itemBidsTv;

        @BindView(R.id.row_auction_item_bids_iv)
        ImageView itemBidStatusIv;

        /* ButterKnife View Injection - END */

        View rootView;

        /**
         * Constructor method.
         * @param itemView Root item view.
         */
        public ViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            ButterKnife.bind(this, itemView);
        }

        /**
         * Sets if the user is winning this item.
         * @param isUserWinning - True if the user is winning this item.
         */
        public void setIsUserWinning(boolean isUserWinning){
            if(isUserWinning){
                itemBidStatusIv.setVisibility(View.VISIBLE);
                itemBidsTv.setTextColor(ContextCompat.getColor(itemBidsTv.getContext(), R.color.colorAccent));
            }else{
                itemBidStatusIv.setVisibility(View.GONE);
                itemBidsTv.setTextColor(ContextCompat.getColor(itemBidsTv.getContext(), R.color.grey_600));
            }
        }
    }
}
