package com.felipeporge.sample.data.mappers;

import com.felipeporge.sample.data.entities.Item;
import com.felipeporge.sample.domain.entities.SampleItem;
import com.felipeporge.sample.domain.mapper.DataMapper;

/**
 * This class represents an item data mapper.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 24/11/2016
 */
public class ItemMapper extends DataMapper<SampleItem, Item> {

    @Override
    public Item transform(SampleItem object) {
        if(object == null)
            return null;

        Item result = new Item();
        result.setId(object.getId());
        result.setTitle(object.getTitle());
        result.setDescription(object.getDescription());
        result.setImgUrl(object.getImgUrl());
        return result;
    }

    @Override
    public SampleItem parseBack(Item object) {
        if(object == null)
            return null;

        SampleItem result = new SampleItem();
        result.setId(object.getId());
        result.setTitle(object.getTitle());
        result.setDescription(object.getDescription());
        result.setImgUrl(object.getImgUrl());

        return result;
    }
}
