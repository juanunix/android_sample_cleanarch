package com.felipeporge.auction.domain.entities;

/**
 * This class represents an auction item.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public class AuctionItem {
    private long id;
    private long user_id;
    private int bid_count;

    private String title;
    private String description;
    private String img;

    private float starting_bid;
    private AuctionBid current_bid;

    private long finish_at;


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

    public int getBidCount() {
        return bid_count;
    }

    public void setBidCount(int bid_count) {
        this.bid_count = bid_count;
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

    public AuctionBid getCurrentBid() {
        return current_bid;
    }

    public void setCurrentBid(AuctionBid bid) {
        this.current_bid = bid;
    }

    /* Getters and Setters - END */
}
