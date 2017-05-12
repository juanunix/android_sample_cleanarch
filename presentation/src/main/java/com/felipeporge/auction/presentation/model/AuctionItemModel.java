package com.felipeporge.auction.presentation.model;

import java.io.Serializable;

/**
 * This class represents an auction item model for the presentation layer.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public class AuctionItemModel implements Serializable {

    private long id;
    private long userId;

    private String img;
    private String title;
    private String description;

    private int bidCount;
    private float startingBid;
    private AuctionBidModel currentBid;

    private long finishAt;

    /**
     * Default constructor method.
     */
    public AuctionItemModel(){}

    /**
     * Constructor method.
     * @param img Item image path.
     * @param title Item title.
     * @param description Item description.
     * @param startingBid Item starting bid.
     * @param finishAt Item end date in millis.
     */
    public AuctionItemModel(String img, String title, String description, float startingBid, long finishAt) {
        this.img = img;
        this.title = title;
        this.description = description;
        this.startingBid = startingBid;
        this.finishAt = finishAt;
    }


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

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String mImagePath) {
        this.img = mImagePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String mTitle) {
        this.title = mTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String mDescription) {
        this.description = mDescription;
    }

    public int getBidCount() {
        return bidCount;
    }

    public void setBidCount(int mBidCount) {
        this.bidCount = mBidCount;
    }

    public float getStartingBid() {
        return startingBid;
    }

    public void setStartingBid(float mStartingBid) {
        this.startingBid = mStartingBid;
    }

    public long getEndDateMillis() {
        return finishAt;
    }

    public void setEndDateMillis(long mEndDateMillis) {
        this.finishAt = mEndDateMillis;
    }

    public AuctionBidModel getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(AuctionBidModel currentBid) {
        this.currentBid = currentBid;
    }

    /* Getters and Setters - END */


    /**
     * Gets current bid value.
     * @return Current bid value.
     */
    public float getCurrentBidValue(){
        if(currentBid != null)
            return currentBid.getValue();
        return startingBid;
    }

    @Override
    public boolean equals(Object obj) {

        if(!(obj instanceof AuctionItemModel))
            return false;

        AuctionItemModel model = (AuctionItemModel) obj;

        if(model.getId() != this.id)
            return false;

        if(model.getUserId() != this.userId)
            return false;

        if(!model.getImg().equals(this.img))
            return false;

        if(!model.getTitle().equals(this.title))
            return false;

        if(!model.getDescription().equals(this.description))
            return false;

        if(model.getBidCount() != this.bidCount)
            return false;

        if(model.getStartingBid() != this.startingBid)
            return false;

        if(model.finishAt != this.finishAt)
            return false;

        if(model.getCurrentBid() == null && this.currentBid != null)
            return false;

        if(model.getCurrentBid() != null && this.currentBid == null)
            return false;

        if(this.currentBid != null && model.getCurrentBid() != null && !this.currentBid.equals(model.getCurrentBid()))
            return false;

        return true;
    }
}
