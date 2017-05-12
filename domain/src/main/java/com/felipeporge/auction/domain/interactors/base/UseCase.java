package com.felipeporge.auction.domain.interactors.base;

import com.felipeporge.auction.domain.executors.PostExecutorThread;
import com.felipeporge.auction.domain.executors.TaskExecutor;

/**
 * This class represents a basic user case.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public abstract class UseCase<REPO, PARAM, RESULT> implements Runnable {

    private TaskExecutor executor;
    private PostExecutorThread postExecutor;
    private REPO repository;
    private UseCaseCallback<RESULT> callback;
    private PARAM params;

    /**
     * Constructor method.
     * @param executor Executor thread.
     * @param postExecutor Post executor thread.
     * @param repository Repository.
     */
    public UseCase(TaskExecutor executor, PostExecutorThread postExecutor, REPO repository) {
        this.executor = executor;
        this.postExecutor = postExecutor;
        this.repository = repository;
    }

    /**
     * Gets the use case repository.
     * @return Use case repository.
     */
    public REPO getRepository(){
        return this.repository;
    }

    /**
     * Gets the use case params.
     * @return Use case params.
     */
    public PARAM getParams(){
        return this.params;
    }

    /**
     * Executes this use case.
     * @param param Use case param.
     * @param callback Use case callback.
     */
    public void execute(PARAM param, UseCaseCallback<RESULT> callback){
        this.callback = callback;
        this.params = param;
        this.executor.run(this);
    }

    /**
     * Notifies post executor thread of the success.
     * @param result Use case result.
     */
    public void notifySuccess(final RESULT result){
        postExecutor.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(result);
            }
        });
    }

    /**
     * Notifies post executor thread of a failure.
     * @param exception Occurred exception.
     */
    public void notifyFailure(final Exception exception){
        postExecutor.post(new Runnable() {
            @Override
            public void run() {
                callback.onFailure(exception);
            }
        });
    }
}
