package com.felipeporge.sample.presentation.views.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.felipeporge.sample.R;
import com.felipeporge.sample.presentation.model.SampleItemModel;
import com.felipeporge.sample.presentation.views.adapters.base.OnRecyclerViewItemClicked;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * This class represents a sample item recyclerview adapter.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public class ItemRvAdapter extends RecyclerView.Adapter<ItemRvAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<SampleItemModel> mData = new ArrayList<>();

    private OnRecyclerViewItemClicked<SampleItemModel> mListener;

    /**
     * Constructor method.
     */
    public ItemRvAdapter(Context context){
        mContext = context;
    }

    /**
     * Constructor method.
     */
    public ItemRvAdapter(Context context, ArrayList<SampleItemModel> data){
        mData = data;
        mContext = context;
    }

    /**
     * Sets on item click listener.
     * @param listener - Listener instance.
     */
    public void setOnItemClickListener(OnRecyclerViewItemClicked<SampleItemModel> listener){
        mListener = listener;
    }

    @Override
    public ItemRvAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.row_item, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(final ItemRvAdapter.ViewHolder holder, int position) {
        SampleItemModel item = mData.get(position);

        holder.itemTitleTv.setText(item.getTitle());
        try {
            holder.imageSdv.setImageURI(Uri.parse(item.getImgUrl()));
        }catch(Exception e){
            Log.w(ItemRvAdapter.class.getSimpleName(), "Invalid image url.");
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
    public void setData(ArrayList<SampleItemModel> data) {
        this.mData = data;
        notifyDataSetChanged();
    }

    /**
     * Gets an item.
     * @param position Item position.
     * @return {@link SampleItemModel}.
     */
    public SampleItemModel getItem(int position){
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

        @BindView(R.id.row_item_img_sdv)
        SimpleDraweeView imageSdv;

        @BindView(R.id.row_item_title_tv)
        TextView itemTitleTv;

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
    }
}
