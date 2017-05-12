package com.felipeporge.auction.domain.entities;

/**
 * This class represents an auction bid.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public class AuctionBid {

    private long id;
    private long user_id;
    private long item_id;
    private float value;
    private long created_at;

    /* Getters and Setters - BEGIN */

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return user_id;
    }

    public void setUserId(long user_id) {
        this.user_id = user_id;
    }

    public long getItemId() {
        return item_id;
    }

    public void setItemId(long item_id) {
        this.item_id = item_id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public long getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(long created_at) {
        this.created_at = created_at;
    }

    /* Getters and Setters - END */
}
