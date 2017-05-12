package com.felipeporge.auction.data.entities;

/**
 * This class represents an user on data layer.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public class User {

    private long _id;
    private String name;
    private String email;
    private String doc;
    private String password;
    private String birthday;

    /* Getters and Setters - BEGIN */

    public long getId() {
        return _id;
    }

    public void setId(long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /* Getters and Setters - END */

    @Override
    public String toString() {
        return "User = { " + getId() + ", " + getName() + ", " + getPassword() + ", " + getEmail() + ", " + getDoc() + ", " + getBirthday() + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;

        return this.toString().equals(obj.toString());
    }
}
