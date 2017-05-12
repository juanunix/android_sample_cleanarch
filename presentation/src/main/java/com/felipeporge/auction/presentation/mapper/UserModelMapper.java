package com.felipeporge.auction.presentation.mapper;

import com.felipeporge.auction.domain.entities.AuctionUser;
import com.felipeporge.auction.domain.mapper.DataMapper;
import com.felipeporge.auction.presentation.model.UserModel;

/**
 * This class represents an user model mapper.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public class UserModelMapper extends DataMapper<UserModel, AuctionUser> {

    @Override
    public AuctionUser transform(UserModel object) {
        if(object == null)
            return null;

        AuctionUser result = new AuctionUser();

        result.setName(object.getName());
        result.setBirthday(object.getBirthday());
        result.setDocCode(object.getDocCode());
        result.setEmail(object.getEmail());
        result.setPassword(object.getPassword());
        result.setId(object.getId());

        return result;
    }

    @Override
    public UserModel parseBack(AuctionUser object) {
        if(object == null)
            return null;

        UserModel result = new UserModel();

        result.setName(object.getName());
        result.setBirthday(object.getBirthday());
        result.setDocCode(object.getDocCode());
        result.setEmail(object.getEmail());
        result.setPassword(object.getPassword());
        result.setId(object.getId());

        return result;
    }
}
