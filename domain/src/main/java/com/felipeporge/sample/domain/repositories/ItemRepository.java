package com.felipeporge.sample.domain.repositories;

import com.felipeporge.sample.domain.entities.SampleItem;
import com.felipeporge.sample.domain.repositories.base.RepositoryCallback;

import java.util.ArrayList;

/**
 * This class represents the sample items repository.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public interface ItemRepository {

    /**
     * Gets all items.
     * @param callback Callback.
     */
    void getAllItems(RepositoryCallback<ArrayList<SampleItem>> callback);
}
