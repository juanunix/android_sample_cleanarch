package com.felipeporge.sample.presentation.model;

import java.io.Serializable;

/**
 * This class represents a sample item model for the presentation layer.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public class SampleItemModel implements Serializable {

    private long id;

    private String imgUrl;
    private String title;
    private String description;

    /**
     * Default constructor method.
     */
    public SampleItemModel(){}

    /**
     * Constructor method.
     * @param img Item image path.
     * @param title Item title.
     * @param description Item description.
     */
    public SampleItemModel(String img, String title, String description) {
        this.imgUrl = img;
        this.title = title;
        this.description = description;
    }


    /* Getters and Setters - BEGIN */

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String mImagePath) {
        this.imgUrl = mImagePath;
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

    /* Getters and Setters - END */

    @Override
    public boolean equals(Object obj) {

        if(!(obj instanceof SampleItemModel))
            return false;

        SampleItemModel model = (SampleItemModel) obj;

        if(model.getId() != this.id)
            return false;

        if(!model.getImgUrl().equals(this.imgUrl))
            return false;

        if(!model.getTitle().equals(this.title))
            return false;

        if(!model.getDescription().equals(this.description))
            return false;

        return true;
    }
}
