package com.felipeporge.auction.presentation.model;

import java.io.Serializable;

/**
 * This class represents a bid in the presentation layer.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 24/11/2016
 */
public class AuctionBidModel implements Serializable {

    private long id;
    private long userId;
    private long itemId;

    private float value;
    private long createdAt;

    /* Getters and Setters - BEGIN */

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long user_id) {
        this.userId = user_id;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long item_id) {
        this.itemId = item_id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long created_at) {
        this.createdAt = created_at;
    }

    /* Getters and Setters - END */

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof AuctionBidModel))
            return false;

        AuctionBidModel model = (AuctionBidModel) obj;

        if(model.getId() != this.id)
            return false;

        if(model.getUserId() != this.userId)
            return false;

        if(model.getItemId() != this.itemId)
            return false;

        if(model.getValue() != this.value)
            return false;

        if(model.getCreatedAt() != this.createdAt)
            return false;

        return true;
    }
}
