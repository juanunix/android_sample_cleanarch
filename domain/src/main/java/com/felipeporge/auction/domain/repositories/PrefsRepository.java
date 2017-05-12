package com.felipeporge.auction.domain.repositories;

import com.felipeporge.auction.domain.repositories.base.RepositoryCallback;

/**
 * This class represents the preferences repository.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public interface PrefsRepository {

    /**
     * Gets if the walkthrough was already showed.
     * @param callback Callback.
     */
    void getShowedWalkthrough(RepositoryCallback<Boolean> callback);

    /**
     * Puts if the walkthrough was already showed.
     * @param showedWalkthrough Walkthrough state.
     * @param callback Callback.
     */
    void putShowedWalkthrough(boolean showedWalkthrough, RepositoryCallback<Void> callback);
}
