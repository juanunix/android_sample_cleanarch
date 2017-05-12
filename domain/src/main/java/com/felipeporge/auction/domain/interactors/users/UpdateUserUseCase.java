package com.felipeporge.auction.domain.interactors.users;

import com.felipeporge.auction.domain.entities.AuctionUser;
import com.felipeporge.auction.domain.executors.PostExecutorThread;
import com.felipeporge.auction.domain.executors.TaskExecutor;
import com.felipeporge.auction.domain.interactors.base.UseCase;
import com.felipeporge.auction.domain.repositories.UserRepository;
import com.felipeporge.auction.domain.repositories.base.RepositoryCallback;

import java.security.InvalidParameterException;

/**
 * This class represents an use case to update a new user.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public class UpdateUserUseCase extends UseCase<UserRepository,AuctionUser,AuctionUser> {

    /**
     * Constructor method.
     * @param executor Executor thread.
     * @param postExecutor Post executor thread.
     * @param repository Repository.
     */
    public UpdateUserUseCase(TaskExecutor executor, PostExecutorThread postExecutor, UserRepository repository) {
        super(executor, postExecutor, repository);
    }

    @Override
    public void run() {
        AuctionUser user = getParams();
        if(user == null){
            notifyFailure(new InvalidParameterException());
        }else {
            getRepository().updateUser(user, new RepositoryCallback<AuctionUser>() {
                @Override
                public void onRepoSuccess(AuctionUser auctionUser) {
                    UpdateUserUseCase.this.notifySuccess(auctionUser);
                }

                @Override
                public void onRepoFailure(Exception exception) {
                    UpdateUserUseCase.this.notifyFailure(exception);
                }
            });
        }
    }
}
