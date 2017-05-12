package com.felipeporge.auction.domain.repositories;

import com.felipeporge.auction.domain.entities.AuctionUser;
import com.felipeporge.auction.domain.repositories.base.RepositoryCallback;

/**
 * This class represents the user repository.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public interface UserRepository {

    /**
     * Creates a new user.
     * @param user User to be created.
     * @param callback The callback.
     */
    void createUser(AuctionUser user, RepositoryCallback<AuctionUser> callback);

    /**
     * Updates an existing user.
     * @param user User to update.
     * @param callback The callback.
     */
    void updateUser(AuctionUser user, RepositoryCallback<AuctionUser> callback);

    /**
     * Gets an user by his credentials.
     * @param credentials Credentials (e.g.: "email:password").
     * @param callback The callback.
     */
    void getUserByCredentials(String credentials, RepositoryCallback<AuctionUser> callback);

    /**
     * Gets an user by his e-mail.
     * @param email User's email.
     * @param callback The callback.
     */
    void getUserByEmail(String email, RepositoryCallback<AuctionUser> callback);
}
