package com.felipeporge.auction.presentation.model;

import java.io.Serializable;

/**
 * This class represents an user model in the presentation layer.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public class UserModel implements Serializable {

    private long id;
    private String name;
    private String docCode;
    private String email;
    private String password;
    private String birthday;

    /* Getters and Setters - BEGIN */

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /* Getters and Setters - END */

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof UserModel))
            return false;

        UserModel model = (UserModel) obj;

        if(model.getId() != this.id)
            return false;

        if(!model.getName().equals(this.name))
            return false;

        if(!model.getDocCode().equals(this.docCode))
            return false;

        if(!model.getBirthday().equals(this.birthday))
            return false;

        if(!model.getPassword().equals(this.password))
            return false;

        return true;
    }
}
