package com.felipeporge.auction.domain.interactors.prefs;

import com.felipeporge.auction.domain.executors.PostExecutorThread;
import com.felipeporge.auction.domain.executors.TaskExecutor;
import com.felipeporge.auction.domain.interactors.base.UseCase;
import com.felipeporge.auction.domain.repositories.PrefsRepository;
import com.felipeporge.auction.domain.repositories.base.RepositoryCallback;

/**
 * This class represents the use case to get if the walkthrough was already showed.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public class GetShowedWalkthroughUseCase extends UseCase<PrefsRepository, Void, Boolean> {

    /**
     * Constructor method.
     *
     * @param executor     Executor thread.
     * @param postExecutor Post executor thread.
     * @param repository   Repository.
     */
    public GetShowedWalkthroughUseCase(TaskExecutor executor, PostExecutorThread postExecutor, PrefsRepository repository) {
        super(executor, postExecutor, repository);
    }


    @Override
    public void run() {
        getRepository().getShowedWalkthrough(new RepositoryCallback<Boolean>() {
            @Override
            public void onRepoSuccess(Boolean result) {
                GetShowedWalkthroughUseCase.this.notifySuccess(result);
            }

            @Override
            public void onRepoFailure(Exception exception) {
                GetShowedWalkthroughUseCase.this.notifyFailure(exception);
            }
        });
    }
}
