package com.felipeporge.auction.data.entities;

/**
 * This class represents an item.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public class Item {

    private long _id;
    private long user_id;
    private String title;
    private String description;
    private String img;
    private float starting_bid;
    private Bid current_bid;
    private long finish_at;
    private int bid_count;

    /* Getters and Setters - BEGIN */

    public long getId() {
        return _id;
    }

    public void setId(long id) {
        this._id = id;
    }

    public long getUserId() {
        return user_id;
    }

    public void setUserId(long user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public float getStartingBid() {
        return starting_bid;
    }

    public void setStartingBid(float starting_bid) {
        this.starting_bid = starting_bid;
    }

    public long getFinishAt() {
        return finish_at;
    }

    public void setFinishAt(long finish_at) {
        this.finish_at = finish_at;
    }

    public int getBidCount() {
        return bid_count;
    }

    public void setBidCount(int bid_count) {
        this.bid_count = bid_count;
    }

    public Bid getCurrentBid() {
        return current_bid;
    }

    public void setCurrentBid(Bid current_bid_value) {
        this.current_bid = current_bid_value;
    }

    /* Getters and Setters - END */

    /**
     * Gets current bid value.
     * @return Bid value.
     */
    public float getCurrentBidValue(){
        if(current_bid != null)
            return current_bid.getValue();
        return starting_bid;
    }

    @Override
    public String toString() {
        String bid = "";
        if(getCurrentBid() != null)
            bid = ", " + getCurrentBid().toString();

        return "Item = { " + getId() + ", " + getUserId() + ", " + getTitle() + ", " + getDescription() + ", "
                + getFinishAt() + ", " + getStartingBid() + ", " + getImg() + ", " + getBidCount() + bid + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;

        return this.toString().equals(obj.toString());
    }
}
