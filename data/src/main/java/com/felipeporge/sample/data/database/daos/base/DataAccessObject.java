package com.felipeporge.sample.data.database.daos.base;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * This class represents the base data acess object.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public abstract class DataAccessObject<T> {

    private SQLiteDatabase mDatabase;

    /**
     * Constructor method.
     * @param writableDb Writable database instance.
     */
    public DataAccessObject(SQLiteDatabase writableDb){
        mDatabase = writableDb;
    }

    /**
     * Creates a new object.
     * @param object Object to create.
     * @return Created object.
     */
    public abstract T create(T object) throws Exception;

    /**
     * Updates an object.
     * @param object Object to update.
     * @return Updated object.
     */
    public abstract T update(T object) throws Exception;

    /**
     * Get an specific object.
     * @param id Object id.
     * @return Object instance.
     */
    public abstract T get(long id) throws Exception;

    /**
     * Removes a specific object.
     * @param id Object id.
     * @return True (if the object was removed) or false (otherwise).
     */
    public abstract boolean remove(long id) throws Exception;

    /**
     * Parses cursor to object.
     * @param cursor Cursor.
     * @return Object instance.
     */
    public abstract T parse(Cursor cursor) throws Exception;

    /**
     * Gets all objects.
     * @return Array of objects.
     */
    public abstract ArrayList<T> getAll() throws Exception;

    /**
     * Gets the database.
     * @return Database.
     */
    public SQLiteDatabase getDatabase(){
        return mDatabase;
    }

    /**
     * Prints all users in Log cat.
     */
    public void dumb(String tableName) throws Exception{
        Log.w(DataAccessObject.class.getSimpleName(), "======= " + tableName + " TABLE - BEGIN =======");
        ArrayList<T> objects = getAll();
        for(T obj : objects){
            Log.w(DataAccessObject.class.getSimpleName(), obj.toString());
        }
        Log.w(DataAccessObject.class.getSimpleName(), "======= " + tableName + " TABLE - END =======");
    }


}
