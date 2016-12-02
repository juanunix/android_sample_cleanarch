package com.felipeporge.sample.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.felipeporge.sample.data.database.schema.SampleDbSchema;

/**
 * This class represents the database helper.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper sDatabaseHelper = null;
    private static SQLiteDatabase sDatabase = null;

    /**
     * Private constructor method.
     * @param context The context.
     */
    private DatabaseHelper(Context context) {
        super(context, SampleDbSchema.DATABASE_NAME, null, SampleDbSchema.DATABASE_VERSION);
    }

    /**
     * Gets the current instance of database helper.
     * @param context The context.
     * @return {@link DatabaseHelper} instance.
     */
    public static DatabaseHelper getInstance(Context context){
        if(sDatabaseHelper == null){
            sDatabaseHelper = new DatabaseHelper(context);
        }
        return sDatabaseHelper;
    }

    /**
     * Builds a new writable database.
     * @param context The context.
     * @return {@link SQLiteDatabase} instance.
     */
    public static SQLiteDatabase getDatabase(Context context){
        if(sDatabase == null){
            sDatabase =  DatabaseHelper.getInstance(context).getWritableDatabase();
        }
        return sDatabase;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(SampleDbSchema.ItemsTable.SQL_CREATE);

        for(String line : SampleDbSchema.ItemsTable.SQL_INSERT_LINES)
            database.execSQL(line);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL(SampleDbSchema.ItemsTable.SQL_DROP);

        onCreate(database);
    }
}
