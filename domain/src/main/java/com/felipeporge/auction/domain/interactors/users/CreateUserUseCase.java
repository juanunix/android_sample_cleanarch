package com.felipeporge.auction.domain.interactors.users;

import com.felipeporge.auction.domain.entities.AuctionUser;
import com.felipeporge.auction.domain.executors.PostExecutorThread;
import com.felipeporge.auction.domain.executors.TaskExecutor;
import com.felipeporge.auction.domain.interactors.base.UseCase;
import com.felipeporge.auction.domain.repositories.UserRepository;
import com.felipeporge.auction.domain.repositories.base.RepositoryCallback;

import java.security.InvalidParameterException;

/**
 * This class represents an use case to create a new user.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public class CreateUserUseCase extends UseCase<UserRepository, AuctionUser, AuctionUser> {

    /**
     * Constructor method.
     * @param executor Executor thread.
     * @param postExecutor Post executor thread.
     * @param repository Repository.
     */
    public CreateUserUseCase(TaskExecutor executor, PostExecutorThread postExecutor, UserRepository repository) {
        super(executor, postExecutor, repository);
    }

    @Override
    public void run() {
        AuctionUser user = getParams();

        if(user == null){
            notifyFailure(new InvalidParameterException());
        }else{
            getRepository().createUser(getParams(), new RepositoryCallback<AuctionUser>() {
                @Override
                public void onRepoSuccess(AuctionUser auctionUser) {
                    CreateUserUseCase.this.notifySuccess(auctionUser);
                }

                @Override
                public void onRepoFailure(Exception exception) {
                    CreateUserUseCase.this.notifyFailure(exception);
                }
            });
        }
    }
}
