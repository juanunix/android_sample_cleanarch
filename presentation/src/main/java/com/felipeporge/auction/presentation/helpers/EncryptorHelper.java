package com.felipeporge.auction.presentation.helpers;

import android.util.Base64;

/**
 * This class allows to encrypts a string.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public class EncryptorHelper {

    /**
     * Encrypts a string.
     * @param str String to encrypt.
     * @return Encrypted string.
     */
    public static String encrypt(String str){
        return Base64.encodeToString(str.getBytes(), Base64.NO_WRAP);
    }
}
