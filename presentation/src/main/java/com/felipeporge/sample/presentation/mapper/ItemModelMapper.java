package com.felipeporge.sample.presentation.mapper;

import com.felipeporge.sample.domain.entities.SampleItem;
import com.felipeporge.sample.domain.mapper.DataMapper;
import com.felipeporge.sample.presentation.model.SampleItemModel;

/**
 * This class represents the item model mapper.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 24/11/2016
 */
public class ItemModelMapper extends DataMapper<SampleItemModel, SampleItem> {

    @Override
    public SampleItem transform(SampleItemModel object) {
        if(object == null)
            return null;

        SampleItem item = new SampleItem();
        item.setId(object.getId());
        item.setTitle(object.getTitle());
        item.setDescription(object.getDescription());
        item.setImgUrl(object.getImgUrl());
        return item;
    }

    @Override
    public SampleItemModel parseBack(SampleItem object) {
        if(object == null)
            return null;

        SampleItemModel item = new SampleItemModel();
        item.setId(object.getId());
        item.setTitle(object.getTitle());
        item.setDescription(object.getDescription());
        item.setImgUrl(object.getImgUrl());
        return item;
    }
}
