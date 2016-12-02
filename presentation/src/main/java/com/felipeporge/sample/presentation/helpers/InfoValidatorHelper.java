package com.felipeporge.sample.presentation.helpers;

/**
 * This class validates user information.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public class InfoValidatorHelper {

    /**
     * Validates the user full name.
     * @param fullname - User full name.
     * @return - True (if is valid) or False (otherwise).
     */
    public static boolean validateFullName(String fullname){
        if(fullname == null || !fullname.matches("^[A-Za-z]{3,}(\\s[A-Za-z]+[\\.]{0,1})+[ ]*"))
            return false;

        return true;
    }

    /**
     * Validates the email.
     * @param email - Email.
     * @return - True (if is valid) or False (otherwise).
     */
    public static boolean validateEmail(String email){
        if(email == null  || email.length() == 0 || !email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))
            return false;

        return true;
    }

    /**
     * Validates the password.
     * @param password - Password.
     * @return - True (if is valid) or False (otherwise).
     */
    public static boolean validatePassword(String password){
        if(password == null || !password.matches("^[A-Za-z0-9]{8,12}$"))
            return false;

        return true;
    }
}
