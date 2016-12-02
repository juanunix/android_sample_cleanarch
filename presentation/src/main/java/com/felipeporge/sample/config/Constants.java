package com.felipeporge.sample.config;

/**
 * This class stores all compilation time constants.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public abstract class Constants {

    /* Splash screen constants */
    public abstract class Splash {
        public static final long SPLASH_DELAY_MS = 1500;
    }

    /* Login screen constants */
    public abstract class Login {
        public static final long LOGIN_ENTRANCE_DELAY = 1000;
    }

    /* Registration screen constants */
    public abstract class SignUp {
        public static final long MINIMUM_AGE_MILLIS = 441849600; // 16 y.o.
    }
}
