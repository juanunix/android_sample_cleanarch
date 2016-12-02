package com.felipeporge.sample.data.repositories;

import com.felipeporge.sample.data.database.daos.ItemDao;
import com.felipeporge.sample.data.entities.Item;
import com.felipeporge.sample.data.mappers.ItemMapper;
import com.felipeporge.sample.domain.entities.SampleItem;
import com.felipeporge.sample.domain.repositories.ItemRepository;
import com.felipeporge.sample.domain.repositories.base.RepositoryCallback;

import java.util.ArrayList;

/**
 * This class represents represents the implementation of an item repository.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 24/11/2016
 */
public class ItemRepositoryImpl implements ItemRepository {

    private ItemMapper mMapper;
    private ItemDao mItemDao;

    /**
     * Constructor method.
     * @param mapper Data mapper.
     * @param dao Item dao.
     */
    public ItemRepositoryImpl(ItemMapper mapper, ItemDao dao){
        mMapper = mapper;
        mItemDao = dao;
    }

    @Override
    public void getAllItems(RepositoryCallback<ArrayList<SampleItem>> callback) {
        try {
            ArrayList<Item> items = mItemDao.getAll();
            callback.onRepoSuccess(mMapper.parseBack(items));
        }catch (Exception e){
            e.printStackTrace();
            callback.onRepoFailure(e);
        }
    }
}
