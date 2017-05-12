package com.felipeporge.auction.presentation.views.adapters.base;

/**
 * This class handles recycler view item clicks.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public interface OnRecyclerViewItemClicked<T> {

    /**
     * Gets on item clicked.
     * @param position Item position.
     */
    void onItemClicked(int position, T item);
}
