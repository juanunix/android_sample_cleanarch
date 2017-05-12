package com.felipeporge.auction.domain.interactors.prefs;

import com.felipeporge.auction.domain.executors.PostExecutorThread;
import com.felipeporge.auction.domain.executors.TaskExecutor;
import com.felipeporge.auction.domain.interactors.base.UseCase;
import com.felipeporge.auction.domain.repositories.PrefsRepository;
import com.felipeporge.auction.domain.repositories.base.RepositoryCallback;

/**
 * This class represents the use case to put if the walkthrough was already showed.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public class PutShowedWalkthroughUseCase extends UseCase<PrefsRepository, Boolean, Void> {

    /**
     * Constructor method.
     *
     * @param executor     Executor thread.
     * @param postExecutor Post executor thread.
     * @param repository   Repository.
     */
    public PutShowedWalkthroughUseCase(TaskExecutor executor, PostExecutorThread postExecutor, PrefsRepository repository) {
        super(executor, postExecutor, repository);
    }


    @Override
    public void run() {
        boolean showedWalkthrough = getParams();

        getRepository().putShowedWalkthrough(showedWalkthrough, new RepositoryCallback<Void>() {
            @Override
            public void onRepoSuccess(Void result) {
                PutShowedWalkthroughUseCase.this.notifySuccess(result);
            }

            @Override
            public void onRepoFailure(Exception exception) {
                PutShowedWalkthroughUseCase.this.notifyFailure(exception);
            }
        });
    }
}
