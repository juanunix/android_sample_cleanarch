package com.felipeporge.sample.domain.interactors.items;

import com.felipeporge.sample.domain.entities.SampleItem;
import com.felipeporge.sample.domain.executors.PostExecutorThread;
import com.felipeporge.sample.domain.executors.TaskExecutor;
import com.felipeporge.sample.domain.interactors.base.UseCase;
import com.felipeporge.sample.domain.repositories.ItemRepository;
import com.felipeporge.sample.domain.repositories.base.RepositoryCallback;

import java.util.ArrayList;

/**
 * This class represents the use case to get all sample items.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public class GetAllItemsUseCase extends UseCase<ItemRepository, Void, ArrayList<SampleItem>> {

    /**
     * Constructor method.
     * @param executor Executor thread.
     * @param postExecutor Post executor thread.
     * @param repository Repository.
     */
    public GetAllItemsUseCase(TaskExecutor executor, PostExecutorThread postExecutor, ItemRepository repository) {
        super(executor, postExecutor, repository);
    }

    @Override
    public void run() {
//        Object params = getParams();

        getRepository().getAllItems(new RepositoryCallback<ArrayList<SampleItem>>() {
            @Override
            public void onRepoSuccess(ArrayList<SampleItem> items) {
                GetAllItemsUseCase.this.notifySuccess(items);
            }

            @Override
            public void onRepoFailure(Exception exception) {
                GetAllItemsUseCase.this.notifyFailure(exception);
            }
        });
    }
}