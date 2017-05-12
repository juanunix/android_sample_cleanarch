package com.felipeporge.auction.data.database.schema;

import android.provider.BaseColumns;

import com.felipeporge.auction.data.config.Constants;
import com.felipeporge.auction.data.database.schema.base.DatabaseSchema;

/**
 * This class represents the auction database schema.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public abstract class AuctionDbSchema extends DatabaseSchema {

    public static final String DATABASE_NAME = "auction.db";
    public static final int DATABASE_VERSION = 1;

    /**
     * This class represents the Users table schema.
     */
    public static abstract class UsersTable implements BaseColumns {
        public static final String TABLE_NAME = "users";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_DOC = "doc";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_BIRTHDAY = "birthday";

        public static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                _ID + TYPE_INTEGER + PRIMARY_KEY + ", " +
                COLUMN_NAME + TYPE_TEXT + ", " +
                COLUMN_EMAIL + TYPE_TEXT + ", " +
                COLUMN_DOC + TYPE_TEXT + ", " +
                COLUMN_PASSWORD + TYPE_TEXT + ", " +
                COLUMN_BIRTHDAY + TYPE_TEXT +
                ")";

        public static final String SQL_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME ;

        public static final String SQL_INSERT_BASE = "INSERT OR REPLACE INTO " + TABLE_NAME +
                        " (" + COLUMN_NAME + ", " + COLUMN_EMAIL + ", " + COLUMN_DOC + ", "
                        + COLUMN_PASSWORD + ", " + COLUMN_BIRTHDAY + ") VALUES ";

        public static final String[] SQL_INSERT_LINES = {
                SQL_INSERT_BASE + "('AuctionSys_BOT', 'bot@auctionsys.com', 'bot001', 'MTIzNDU2Nzg=', '24/11/2016')",
                SQL_INSERT_BASE + "('Felipe Porge Xavier', 'contact@felipeporge.com', '123456', 'MTIzNDU2Nzg=', '15/10/1990')",
                SQL_INSERT_BASE + "('Sample User', 'user@auctionsys.com', 'sample001', 'MTIzNDU2Nzg=', '12/10/1983')"
        };

    }

    /**
     * This class represents the Items table schema.
     */
    public static abstract class ItemsTable implements BaseColumns {
        public static final String TABLE_NAME = "items";

        public static final String COLUMN_USER_ID = "user_id";

        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_IMG = "img";
        public static final String COLUMN_STARTING_BID = "starting_bid";
        public static final String COLUMN_END_DATE = "finish_at";

        public static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                _ID + TYPE_INTEGER + PRIMARY_KEY + ", " +
                COLUMN_USER_ID + TYPE_INTEGER + ", " +
                COLUMN_TITLE + TYPE_TEXT + ", " +
                COLUMN_DESCRIPTION + TYPE_TEXT + ", " +
                COLUMN_IMG + TYPE_TEXT + ", " +
                COLUMN_STARTING_BID + TYPE_REAL + ", " +
                COLUMN_END_DATE + TYPE_INTEGER + ", " +
                FOREIGN_KEY + " (" + COLUMN_USER_ID + ")" + REFERENCES + UsersTable.TABLE_NAME + "(" + UsersTable._ID + ")" +
                ")";

        public static final String SQL_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME ;

        public static final String SQL_INSERT_BASE = "INSERT OR REPLACE INTO " + TABLE_NAME + " (" +
                COLUMN_USER_ID + ", " + COLUMN_TITLE + ", " + COLUMN_DESCRIPTION + ", " + COLUMN_IMG + ", "
                + COLUMN_STARTING_BID + ", " + COLUMN_END_DATE + ") VALUES ";

        public static final String[] SQL_INSERT_LINES = {
                SQL_INSERT_BASE + "( '1', " + " 'Caloi bike for Children', 'This is an awesome bike for your children. That is an incredible black bike.', 'https://goo.gl/njD4Oa', " +
                        "'299.99', '" + (System.currentTimeMillis() + 2 * Constants.Time.DAY_MS) + "') ",

                SQL_INSERT_BASE + "( '1', " + " 'Brand new Brazilian Flag', 'This is an awesome flag for you. That is an incredible Brazilian flag.', 'https://goo.gl/OQHLVh', " +
                        "'99.38', '" + (System.currentTimeMillis() + Constants.Time.DAY_MS) + "') ",

                SQL_INSERT_BASE + "( '1', " + " 'Beautiful and refreshing shower', 'This is an awesome shower for you. That is an incredible shower.', 'https://goo.gl/kJ6GhL', " +
                        "'42.22', '" + (System.currentTimeMillis() + 20 * Constants.Time.HOUR_MS) + "') ",

                SQL_INSERT_BASE + "( '1', " + " 'Tesla Model S', 'This is an awesome car for you. That is an incredible car.', 'https://goo.gl/YaasxN', " +
                        "'49999.99', '" + (System.currentTimeMillis() + 12 * Constants.Time.HOUR_MS) + "') ",


                SQL_INSERT_BASE + "( '2', " + " 'Black pencil', 'This is a test item.', '', " + "'28.99', '" + (System.currentTimeMillis() + 30 * Constants.Time.MINUTE_MS) + "') ",

                SQL_INSERT_BASE + "( '2', " + " 'Red pencil', 'This is a test item.', '', " + "'24.99', '" + (System.currentTimeMillis() + 20 * Constants.Time.MINUTE_MS) + "') ",

                SQL_INSERT_BASE + "( '2', " + " 'Green pencil', 'This is a test item.', '', " + "'25.99', '" + (System.currentTimeMillis() + 10 * Constants.Time.MINUTE_MS) + "') ",

                SQL_INSERT_BASE + "( '2', " + " 'Pink pencil', 'This is a test item.', '', " + "'32.99', '" + (System.currentTimeMillis() + 5 * Constants.Time.MINUTE_MS) + "') ",


                SQL_INSERT_BASE + "( '3', " + " 'Black chair', 'This is a test item.', '', " + "'59.29', '" + (System.currentTimeMillis() + 30 * Constants.Time.MINUTE_MS) + "') ",

                SQL_INSERT_BASE + "( '3', " + " 'Brown chair', 'This is a test item.', '', " + "'32.49', '" + (System.currentTimeMillis() + 20 * Constants.Time.MINUTE_MS) + "') ",

                SQL_INSERT_BASE + "( '3', " + " 'Gray chair', 'This is a test item.', '', " + "'18.89', '" + (System.currentTimeMillis() + 10 * Constants.Time.MINUTE_MS) + "') ",

                SQL_INSERT_BASE + "( '3', " + " 'Green chair', 'This is a test item.', '', " + "'98.99', '" + (System.currentTimeMillis() + 5 * Constants.Time.MINUTE_MS) + "') "
        };

    }

    /**
     * This class represents the Bids table schema.
     */
    public abstract class BidsTable implements BaseColumns {
        public static final String TABLE_NAME = "bids";

        public static final String COLUMN_USER_ID = "user_id";
        public static final String COLUMN_ITEM_ID = "item_id";

        public static final String COLUMN_VALUE = "value";
        public static final String COLUMN_CREATION_DATE = "created_at";

        public static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                _ID + TYPE_INTEGER + PRIMARY_KEY + ", " +
                COLUMN_USER_ID + TYPE_INTEGER + ", " +
                COLUMN_ITEM_ID + TYPE_INTEGER + ", " +
                COLUMN_VALUE + TYPE_REAL + ", " +
                COLUMN_CREATION_DATE + TYPE_TEXT + ", " +
                FOREIGN_KEY + " (" + COLUMN_USER_ID + ")" + REFERENCES + UsersTable.TABLE_NAME + "(" + UsersTable._ID + "), " +
                FOREIGN_KEY + " (" + COLUMN_ITEM_ID + ")" + REFERENCES + ItemsTable.TABLE_NAME + "(" + ItemsTable._ID + ")" +
                ")";

        public static final String SQL_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME ;
    }
}