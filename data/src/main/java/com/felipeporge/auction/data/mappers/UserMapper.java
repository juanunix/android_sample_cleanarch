package com.felipeporge.auction.data.mappers;

import com.felipeporge.auction.data.entities.User;
import com.felipeporge.auction.domain.entities.AuctionUser;
import com.felipeporge.auction.domain.mapper.DataMapper;

/**
 * This class represents an user mapper.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public class UserMapper extends DataMapper<AuctionUser, User> {

    @Override
    public AuctionUser parseBack(User object) {
        if(object == null)
            return null;

        AuctionUser result = new AuctionUser();

        result.setName(object.getName());
        result.setBirthday(object.getBirthday());
        result.setDocCode(object.getDoc());
        result.setEmail(object.getEmail());
        result.setPassword(object.getPassword());
        result.setId(object.getId());

        return result;
    }

    @Override
    public User transform(AuctionUser object) {
        if(object == null)
            return null;

        User result = new User();

        result.setName(object.getName());
        result.setBirthday(object.getBirthday());
        result.setDoc(object.getDocCode());
        result.setEmail(object.getEmail());
        result.setPassword(object.getPassword());
        result.setId(object.getId());

        return result;
    }
}
