package com.felipeporge.auction.domain.repositories.base;

/**
 * This interface describes some event handling methods.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public interface RepositoryCallback<RESULT> {

    /**
     * This method is called when a repository successfully executed a request.
     * @param result    Repository result.
     */
    void onRepoSuccess(RESULT result);

    /**
     * This method is called when an error occurred during a request.
     * @param exception Exception instance.
     */
    void onRepoFailure(Exception exception);
}
