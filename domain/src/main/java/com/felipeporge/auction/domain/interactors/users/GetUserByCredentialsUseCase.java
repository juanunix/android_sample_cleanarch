package com.felipeporge.auction.domain.interactors.users;

import com.felipeporge.auction.domain.entities.AuctionUser;
import com.felipeporge.auction.domain.executors.PostExecutorThread;
import com.felipeporge.auction.domain.executors.TaskExecutor;
import com.felipeporge.auction.domain.interactors.base.UseCase;
import com.felipeporge.auction.domain.repositories.UserRepository;
import com.felipeporge.auction.domain.repositories.base.RepositoryCallback;

/**
 * This class represents to get an specific user using his credentials (e.g.: "email:password").
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public class GetUserByCredentialsUseCase extends UseCase<UserRepository, String, AuctionUser> {

    /**
     * Constructor method.
     * @param executor Executor thread.
     * @param postExecutor Post executor thread.
     * @param repository Repository.
     */
    public GetUserByCredentialsUseCase(TaskExecutor executor, PostExecutorThread postExecutor, UserRepository repository) {
        super(executor, postExecutor, repository);
    }

    @Override
    public void run() {
        getRepository().getUserByCredentials(getParams(), new RepositoryCallback<AuctionUser>() {
            @Override
            public void onRepoSuccess(AuctionUser auctionUser) {
                GetUserByCredentialsUseCase.this.notifySuccess(auctionUser);
            }

            @Override
            public void onRepoFailure(Exception exception) {
                GetUserByCredentialsUseCase.this.notifyFailure(exception);
            }
        });
    }
}
