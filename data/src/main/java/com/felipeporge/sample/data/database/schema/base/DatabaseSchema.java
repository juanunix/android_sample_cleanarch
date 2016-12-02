package com.felipeporge.sample.data.database.schema.base;

/**
 * This class represents a base database schema.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public abstract class DatabaseSchema {

    public static final String AUTO_INCREMENT = " AUTOINCREMENT";
    public static final String PRIMARY_KEY = " PRIMARY KEY";
    public static final String FOREIGN_KEY = " FOREIGN KEY";
    public static final String REFERENCES = " REFERENCES ";


    /* The value is a signed integer, stored in 1, 2, 3, 4, 6, or 8 bytes depending on the magnitude of the value. */
    public static final String TYPE_INTEGER = " INTEGER";

    /* The value is a text string, stored using the database encoding (UTF-8, UTF-16BE or UTF-16LE) */
    public static final String TYPE_TEXT = " TEXT";

    /* The value is a floating point value, stored as an 8-byte IEEE floating point number. */
    public static final String TYPE_REAL = " REAL";

    /* The value is a NULL value. */
    public static final String TYPE_NULL = " NULL";

    /* The value is a blob of data, stored exactly as it was input. */
    public static final String TYPE_BLOB = " BLOB";
}
