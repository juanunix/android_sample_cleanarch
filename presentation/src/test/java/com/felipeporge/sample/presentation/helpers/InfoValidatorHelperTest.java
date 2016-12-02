package com.felipeporge.sample.presentation.helpers;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This class represents the unit tests for {@link InfoValidatorHelper}.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public class InfoValidatorHelperTest {

    @Test
    public void testValidateFullName_valid() throws Exception {
        assertTrue(InfoValidatorHelper.validateFullName("Felipe Porge"));
        assertTrue(InfoValidatorHelper.validateFullName("Felipe Porge Xavier"));
        assertTrue(InfoValidatorHelper.validateFullName("Felipe P. Xavier"));
        assertTrue(InfoValidatorHelper.validateFullName("Felipe P. X."));
    }

    @Test
    public void testValidateFullName_invalid() throws Exception {
        assertFalse(InfoValidatorHelper.validateFullName("Felipe"));
        assertFalse(InfoValidatorHelper.validateFullName("Felipe "));
        assertFalse(InfoValidatorHelper.validateFullName("Felipe ."));
        assertFalse(InfoValidatorHelper.validateFullName("Felipe . X."));
    }


    @Test
    public void testValidateEmail_valid() throws Exception {
        assertTrue(InfoValidatorHelper.validateEmail("felipe.xavier@elife.com"));
        assertTrue(InfoValidatorHelper.validateEmail("felipe_xavier@elife.com"));
        assertTrue(InfoValidatorHelper.validateEmail("felipe@live.com.br"));
        assertTrue(InfoValidatorHelper.validateEmail("felipe@co.cc"));
    }

    @Test
    public void testValidateEmail_invalid() throws Exception {
        assertFalse(InfoValidatorHelper.validateEmail("luis paulo@g"));
        assertFalse(InfoValidatorHelper.validateEmail("luis@g.c"));
        assertFalse(InfoValidatorHelper.validateEmail("luis.paulo@hotmail.com "));
        assertFalse(InfoValidatorHelper.validateEmail("oi.maria@gmail."));
        assertFalse(InfoValidatorHelper.validateEmail("oi.maria@gmail.com."));
        assertFalse(InfoValidatorHelper.validateEmail(""));
    }

    @Test
    public void testValidatePassword_valid() throws Exception {
        assertTrue(InfoValidatorHelper.validatePassword("12345678"));
        assertTrue(InfoValidatorHelper.validatePassword("a12345678"));
        assertTrue(InfoValidatorHelper.validatePassword("zBfxSlbkM3"));
        assertTrue(InfoValidatorHelper.validatePassword("123xs0oas"));
        assertTrue(InfoValidatorHelper.validatePassword("123xs0oas123"));
    }

    @Test
    public void testValidatePassword_invalid() throws Exception {
        assertFalse(InfoValidatorHelper.validatePassword("123.x23!"));
        assertFalse(InfoValidatorHelper.validatePassword("123 2321"));
        assertFalse(InfoValidatorHelper.validatePassword("123xk213o "));
        assertFalse(InfoValidatorHelper.validatePassword("1xzZ2#,c"));
        assertFalse(InfoValidatorHelper.validatePassword("alo.maria_com"));
    }

}