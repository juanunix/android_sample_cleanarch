package com.felipeporge.auction.domain.entities;

/**
 * This class represents an auction user.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public class AuctionUser {

    private long id;
    private String name;
    private String email;
    private String doc_code;
    private String password;
    private String birthday;

    /* Getters and Setters - BEGIN */

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocCode() {
        return doc_code;
    }

    public void setDocCode(String doc_code) {
        this.doc_code = doc_code;
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
}
