package com.felipeporge.auction.data.database.daos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.felipeporge.auction.data.database.daos.base.DataAccessObject;
import com.felipeporge.auction.data.database.schema.AuctionDbSchema;
import com.felipeporge.auction.data.entities.User;

import java.util.ArrayList;

/**
 * This class represents the user data acess object.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public class UserDao extends DataAccessObject<User> {

    /**
     * Constructor method.
     *
     * @param database Writable database instance.
     */
    public UserDao(SQLiteDatabase database) {
        super(database);
    }

    @Override
    public User create(User user) throws Exception {
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(AuctionDbSchema.UsersTable.COLUMN_NAME, user.getName());
        values.put(AuctionDbSchema.UsersTable.COLUMN_DOC, user.getDoc());
        values.put(AuctionDbSchema.UsersTable.COLUMN_BIRTHDAY, user.getBirthday());
        values.put(AuctionDbSchema.UsersTable.COLUMN_EMAIL, user.getEmail());
        values.put(AuctionDbSchema.UsersTable.COLUMN_PASSWORD, user.getPassword());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = getDatabase().insert(AuctionDbSchema.UsersTable.TABLE_NAME, null, values);

        if(newRowId == -1){
            throw new Exception("Error inserting the user.");
        }

        user.setId(newRowId);

        Log.w(UserDao.class.getSimpleName(), "User created: (" + user.getId() + ", " + user.getName() + ")");
        return user;
    }

    @Override
    public User update(User user) throws Exception {
        // New value for one column
        ContentValues values = new ContentValues();
        values.put(AuctionDbSchema.UsersTable.COLUMN_NAME, user.getName());
        values.put(AuctionDbSchema.UsersTable.COLUMN_DOC, user.getDoc());
        values.put(AuctionDbSchema.UsersTable.COLUMN_BIRTHDAY, user.getBirthday());
        values.put(AuctionDbSchema.UsersTable.COLUMN_EMAIL, user.getEmail());
        values.put(AuctionDbSchema.UsersTable.COLUMN_PASSWORD, user.getPassword());

        // Which row to update, based on the title
        String selection = AuctionDbSchema.UsersTable._ID + " = ?";
        String[] selectionArgs = { String.valueOf(user.getId()) };

        int count = getDatabase().update(
                AuctionDbSchema.UsersTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        if(count <= 0){
            throw new Exception("Error updating the user.");
        }

        return user;
    }

    @Override
    public User get(long userId) throws Exception {
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                AuctionDbSchema.UsersTable._ID,
                AuctionDbSchema.UsersTable.COLUMN_NAME,
                AuctionDbSchema.UsersTable.COLUMN_DOC,
                AuctionDbSchema.UsersTable.COLUMN_BIRTHDAY,
                AuctionDbSchema.UsersTable.COLUMN_EMAIL,
                AuctionDbSchema.UsersTable.COLUMN_PASSWORD
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = AuctionDbSchema.UsersTable._ID + " = ?";
        String[] selectionArgs = { String.valueOf(userId) };

        Cursor cursor = getDatabase().query(
                AuctionDbSchema.UsersTable.TABLE_NAME,     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // The sort order
        );

        if(!cursor.moveToFirst()){
            throw new IllegalArgumentException();
        }

        return this.parse(cursor);
    }

    @Override
    public boolean remove(long userId) throws Exception {
        // Define 'where' part of query.
        String selection = AuctionDbSchema.UsersTable._ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { String.valueOf(userId) };

        // Issue SQL statement.
        int count = getDatabase().delete(AuctionDbSchema.UsersTable.TABLE_NAME, selection, selectionArgs);
        return count > 0;
    }

    @Override
    public User parse(Cursor cursor) throws Exception {

        User user = new User();
        user.setId(
                cursor.getLong(cursor.getColumnIndexOrThrow(AuctionDbSchema.UsersTable._ID))
        );

        user.setName(
                cursor.getString(cursor.getColumnIndexOrThrow(AuctionDbSchema.UsersTable.COLUMN_NAME))
        );

        user.setEmail(
                cursor.getString(cursor.getColumnIndexOrThrow(AuctionDbSchema.UsersTable.COLUMN_EMAIL))
        );

        user.setDoc(
                cursor.getString(cursor.getColumnIndexOrThrow(AuctionDbSchema.UsersTable.COLUMN_DOC))
        );

        user.setBirthday(
                cursor.getString(cursor.getColumnIndexOrThrow(AuctionDbSchema.UsersTable.COLUMN_BIRTHDAY))
        );

        user.setPassword(
                cursor.getString(cursor.getColumnIndexOrThrow(AuctionDbSchema.UsersTable.COLUMN_PASSWORD))
        );
        return user;
    }

    @Override
    public ArrayList<User> getAll() throws Exception {
        Cursor cursor = getDatabase().rawQuery("SELECT * FROM " + AuctionDbSchema.UsersTable.TABLE_NAME, null);
        ArrayList<User> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                User user = this.parse(cursor);
                result.add(user);
                cursor.moveToNext();
            }
        }
        return result;
    }


    /**
     * Gets an user using his email and password.
     * @param email User's email.
     * @param password User's password.
     * @return User.
     */
    public User getUserByEmailAndPassword(String email, String password) throws Exception {
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                AuctionDbSchema.UsersTable._ID,
                AuctionDbSchema.UsersTable.COLUMN_NAME,
                AuctionDbSchema.UsersTable.COLUMN_DOC,
                AuctionDbSchema.UsersTable.COLUMN_BIRTHDAY,
                AuctionDbSchema.UsersTable.COLUMN_EMAIL,
                AuctionDbSchema.UsersTable.COLUMN_PASSWORD
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = AuctionDbSchema.UsersTable.COLUMN_EMAIL + " = ? AND " + AuctionDbSchema.UsersTable.COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = { email, password };

        Cursor cursor = getDatabase().query(
                AuctionDbSchema.UsersTable.TABLE_NAME,     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // The sort order
        );

        if(!cursor.moveToFirst()){
            throw new IllegalArgumentException();
        }

        return this.parse(cursor);
    }

    /**
     * Gets an user using his email
     * @param email User's email.
     * @return User.
     */
    public User getUserByEmail(String email) throws Exception {
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                AuctionDbSchema.UsersTable._ID,
                AuctionDbSchema.UsersTable.COLUMN_NAME,
                AuctionDbSchema.UsersTable.COLUMN_DOC,
                AuctionDbSchema.UsersTable.COLUMN_BIRTHDAY,
                AuctionDbSchema.UsersTable.COLUMN_EMAIL,
                AuctionDbSchema.UsersTable.COLUMN_PASSWORD
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = AuctionDbSchema.UsersTable.COLUMN_EMAIL + " = ?";
        String[] selectionArgs = { email };

        Cursor cursor = getDatabase().query(
                AuctionDbSchema.UsersTable.TABLE_NAME,     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                      // The sort order
        );

        if(!cursor.moveToFirst()){
            throw new IllegalArgumentException();
        }

        return this.parse(cursor);
    }
}
