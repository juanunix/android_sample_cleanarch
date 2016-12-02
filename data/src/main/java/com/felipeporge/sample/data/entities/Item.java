package com.felipeporge.sample.data.entities;

/**
 * This class represents an item.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public class Item {

    private long _id;
    private String title;
    private String description;
    private String imgUrl;

    /* Getters and Setters - BEGIN */

    public long getId() {
        return _id;
    }

    public void setId(long id) {
        this._id = id;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /* Getters and Setters - END */

    @Override
    public String toString() {

        return "Item = { " + getId() + ", " + getTitle() + ", " + getDescription() + ", " + getImgUrl() + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;

        return this.toString().equals(obj.toString());
    }
}
