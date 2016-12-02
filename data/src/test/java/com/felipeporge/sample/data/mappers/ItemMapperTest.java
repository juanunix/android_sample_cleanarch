package com.felipeporge.sample.data.mappers;

import com.felipeporge.sample.data.entities.Item;
import com.felipeporge.sample.domain.entities.SampleItem;

import org.junit.Assert;
import org.junit.Test;

/**
 * This class represents an item mapper test.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 25/11/2016
 */
public class ItemMapperTest {

    @Test
    public void transform_parseBack() throws Exception {
        Item mockedItem = new Item();
        mockedItem.setId(1);
        mockedItem.setTitle("Mocked item");
        mockedItem.setDescription("This is a test item.");
        mockedItem.setImgUrl("http://www.img.com/img.png");

        ItemMapper modelMapper = new ItemMapper();
        SampleItem transfMockedItem = modelMapper.parseBack(mockedItem);

        Assert.assertEquals(mockedItem, modelMapper.transform(transfMockedItem));
    }

}