package com.felipeporge.auction.domain.interactors.users;

import com.felipeporge.auction.domain.entities.AuctionUser;
import com.felipeporge.auction.domain.executors.PostExecutorThread;
import com.felipeporge.auction.domain.executors.TaskExecutor;
import com.felipeporge.auction.domain.interactors.base.UseCase;
import com.felipeporge.auction.domain.repositories.UserRepository;
import com.felipeporge.auction.domain.repositories.base.RepositoryCallback;

import java.security.InvalidParameterException;

/**
 * This class represents the use case that allows to verify if an email was already registered.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public class GetUserByEmailUseCase extends UseCase<UserRepository, String, AuctionUser> {

    /**
     * Constructor method.
     * @param executor Executor thread.
     * @param postExecutor Post executor thread.
     * @param repository Repository.
     */
    public GetUserByEmailUseCase(TaskExecutor executor, PostExecutorThread postExecutor, UserRepository repository) {
        super(executor, postExecutor, repository);
    }

    @Override
    public void run() {
        String email = getParams();
        if(email == null){
            notifyFailure(new InvalidParameterException());
            return;
        }

        getRepository().getUserByEmail(email, new RepositoryCallback<AuctionUser>() {
            @Override
            public void onRepoSuccess(AuctionUser user) {
                GetUserByEmailUseCase.this.notifySuccess(user);
            }

            @Override
            public void onRepoFailure(Exception exception) {
                GetUserByEmailUseCase.this.notifyFailure(exception);
            }
        });
    }
}