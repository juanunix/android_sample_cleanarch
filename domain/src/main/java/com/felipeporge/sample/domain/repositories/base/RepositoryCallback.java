package com.felipeporge.sample.domain.repositories.base;

/**
 * This class represents...
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public interface RepositoryCallback<RESULT> {
    void onRepoSuccess(RESULT result);
    void onRepoFailure(Exception exception);
}
