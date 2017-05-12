package com.felipeporge.auction.data.repositories;

import com.felipeporge.auction.data.database.daos.UserDao;
import com.felipeporge.auction.data.entities.User;
import com.felipeporge.auction.data.mappers.UserMapper;
import com.felipeporge.auction.domain.entities.AuctionUser;
import com.felipeporge.auction.domain.repositories.UserRepository;
import com.felipeporge.auction.domain.repositories.base.RepositoryCallback;

/**
 * This class represents the implementation of an user repository.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public class UserRepositoryImpl implements UserRepository {

    private UserDao mUserDao;
    private UserMapper mUserMapper;

    /**
     * Constructor method.
     * @param mapper Data mapper.
     * @param dao User dao.
     */
    public UserRepositoryImpl(UserMapper mapper, UserDao dao){
        mUserMapper = mapper;
        mUserDao = dao;
    }

    @Override
    public void createUser(AuctionUser user, RepositoryCallback<AuctionUser> callback) {
        try{
            User createdUser = mUserDao.create(mUserMapper.transform(user));
            callback.onRepoSuccess(mUserMapper.parseBack(createdUser));
        }catch(Exception e){
            e.printStackTrace();
            callback.onRepoFailure(e);
        }
    }

    @Override
    public void updateUser(AuctionUser user, RepositoryCallback<AuctionUser> callback) {
        try{
            User updatedUser = mUserDao.update(mUserMapper.transform(user));
            callback.onRepoSuccess(mUserMapper.parseBack(updatedUser));
        }catch(Exception e){
            e.printStackTrace();
            callback.onRepoFailure(e);
        }
    }

    @Override
    public void getUserByCredentials(String credentials, RepositoryCallback<AuctionUser> callback) {
        try{
            String[] creds = credentials.split(":");
            User user = mUserDao.getUserByEmailAndPassword(creds[0], creds[1]);

            callback.onRepoSuccess(mUserMapper.parseBack(user));
        }catch(Exception e){
            e.printStackTrace();
            callback.onRepoFailure(e);
        }
    }

    @Override
    public void getUserByEmail(String email, RepositoryCallback<AuctionUser> callback) {
        try{
            User user = mUserDao.getUserByEmail(email);
            callback.onRepoSuccess(mUserMapper.parseBack(user));
        }catch(Exception e){
            e.printStackTrace();
            callback.onRepoFailure(e);
        }
    }
}
