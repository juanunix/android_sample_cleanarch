package com.felipeporge.sample.data.database.schema;

import android.provider.BaseColumns;

import com.felipeporge.sample.data.database.schema.base.DatabaseSchema;

/**
 * This class represents the sample database schema.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
// TODO: Auction database schema.
public abstract class SampleDbSchema extends DatabaseSchema {

    public static final String DATABASE_NAME = "sample.db";
    public static final int DATABASE_VERSION = 1;

    /**
     * This class represents the Items table schema.
     */
    public static abstract class ItemsTable implements BaseColumns {
        public static final String TABLE_NAME = "items";

        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_IMG_URL = "img";

        public static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                _ID + TYPE_INTEGER + PRIMARY_KEY + ", " +
                COLUMN_TITLE + TYPE_TEXT + ", " +
                COLUMN_DESCRIPTION + TYPE_TEXT + ", " +
                COLUMN_IMG_URL + TYPE_TEXT +
                ")";

        public static final String SQL_DROP = "DROP TABLE IF EXISTS " + TABLE_NAME ;

        public static final String SQL_INSERT_BASE = "INSERT OR REPLACE INTO " + TABLE_NAME + " (" +
                COLUMN_TITLE + ", " + COLUMN_DESCRIPTION + ", " + COLUMN_IMG_URL + ") VALUES ";

        public static final String[] SQL_INSERT_LINES = {
                SQL_INSERT_BASE + "('Caloi bike for Children', 'This is an awesome bike for your children. That is an incredible black bike.', 'https://goo.gl/njD4Oa') ",
                SQL_INSERT_BASE + "('Brand new Brazilian Flag', 'This is an awesome flag for you. That is an incredible Brazilian flag.', 'https://goo.gl/OQHLVh') ",
                SQL_INSERT_BASE + "('Beautiful and refreshing shower', 'This is an awesome shower for you. That is an incredible shower.', 'https://goo.gl/kJ6GhL') ",
                SQL_INSERT_BASE + "('Tesla Model S', 'This is an awesome car for you. That is an incredible car.', 'https://goo.gl/YaasxN') ",
                SQL_INSERT_BASE + "('Black pencil', 'This is an awesome pencil. That is an incredible black pencil.', '') ",
                SQL_INSERT_BASE + "('Green pencil', 'This is an awesome pencil. That is an incredible green pencil.', '') ",
                SQL_INSERT_BASE + "('Red pencil', 'This is an awesome pencil. That is an incredible red pencil.', '') "
        };
    }
}