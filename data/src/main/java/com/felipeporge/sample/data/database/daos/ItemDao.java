package com.felipeporge.sample.data.database.daos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.felipeporge.sample.data.database.daos.base.DataAccessObject;
import com.felipeporge.sample.data.database.schema.SampleDbSchema;
import com.felipeporge.sample.data.entities.Item;

import java.util.ArrayList;

/**
 * This class represents the item data access object.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public class ItemDao extends DataAccessObject<Item> {

    /**
     * Constructor method.
     *
     * @param writableDb Writable database instance.
     */
    public ItemDao(SQLiteDatabase writableDb) {
        super(writableDb);
    }

    @Override
    public Item create(Item item) throws Exception {

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(SampleDbSchema.ItemsTable.COLUMN_TITLE, item.getTitle());
        values.put(SampleDbSchema.ItemsTable.COLUMN_DESCRIPTION, item.getDescription());
        values.put(SampleDbSchema.ItemsTable.COLUMN_IMG_URL, item.getImgUrl());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = getDatabase().insert(SampleDbSchema.ItemsTable.TABLE_NAME, null, values);

        if(newRowId == -1){
            throw new Exception("Error inserting the item.");
        }

        item.setId(newRowId);

        Log.w(ItemDao.class.getSimpleName(), "Item created: (" + item.getId() + ", " + item.getTitle() + ")");
        return item;
    }

    @Override
    public Item update(Item item) throws Exception {

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(SampleDbSchema.ItemsTable.COLUMN_TITLE, item.getTitle());
        values.put(SampleDbSchema.ItemsTable.COLUMN_DESCRIPTION, item.getDescription());
        values.put(SampleDbSchema.ItemsTable.COLUMN_IMG_URL, item.getImgUrl());

        // Which row to update, based on the title
        String selection = SampleDbSchema.ItemsTable._ID + " = ?";
        String[] selectionArgs = { String.valueOf(item.getId()) };

        int count = getDatabase().update(
                SampleDbSchema.ItemsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        if(count <= 0){
            throw  new Exception("Error updating the item.");
        }

        return item;
    }

    @Override
    public Item get(long itemId) throws Exception {
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                SampleDbSchema.ItemsTable._ID,
                SampleDbSchema.ItemsTable.COLUMN_TITLE,
                SampleDbSchema.ItemsTable.COLUMN_DESCRIPTION,
                SampleDbSchema.ItemsTable.COLUMN_IMG_URL,
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = SampleDbSchema.ItemsTable._ID + " = ?";
        String[] selectionArgs = { String.valueOf(itemId) };

        Cursor cursor = getDatabase().query(
                SampleDbSchema.ItemsTable.TABLE_NAME,     // The table to query
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

        return parse(cursor);
    }

    @Override
    public boolean remove(long id) throws Exception {
        // Define 'where' part of query.
        String selection = SampleDbSchema.ItemsTable._ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { String.valueOf(id) };

        // Issue SQL statement.
        int count = getDatabase().delete(SampleDbSchema.ItemsTable.TABLE_NAME, selection, selectionArgs);
        return count > 0;
    }

    @Override
    public Item parse(Cursor cursor) throws Exception {
        Item item = new Item();

        item.setId(
                cursor.getLong(cursor.getColumnIndexOrThrow(SampleDbSchema.ItemsTable._ID))
        );

        item.setTitle(
                cursor.getString(cursor.getColumnIndexOrThrow(SampleDbSchema.ItemsTable.COLUMN_TITLE))
        );

        item.setDescription(
                cursor.getString(cursor.getColumnIndexOrThrow(SampleDbSchema.ItemsTable.COLUMN_DESCRIPTION))
        );

        item.setImgUrl(
                cursor.getString(cursor.getColumnIndexOrThrow(SampleDbSchema.ItemsTable.COLUMN_IMG_URL))
        );

        return item;
    }

    @Override
    public ArrayList<Item> getAll() throws Exception {
        Cursor cursor = getDatabase().rawQuery("SELECT * FROM " + SampleDbSchema.ItemsTable.TABLE_NAME, null);
        ArrayList<Item> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Item item = parse(cursor);
                result.add(item);
                cursor.moveToNext();
            }
        }
        return result;
    }
}
