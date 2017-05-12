package com.felipeporge.auction.data.database.daos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.felipeporge.auction.data.database.daos.base.DataAccessObject;
import com.felipeporge.auction.data.database.schema.AuctionDbSchema;
import com.felipeporge.auction.data.entities.Bid;

import java.util.ArrayList;

/**
 * This class represents the bid data access object.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 24/11/2016
 */
public class BidDao extends DataAccessObject<Bid>{

    /**
     * Constructor method.
     *
     * @param writableDb Writable database instance.
     */
    public BidDao(SQLiteDatabase writableDb) {
        super(writableDb);
    }

    @Override
    public Bid create(Bid bid) throws Exception {
//        try {
//            Bid alreadyExists = getBid(bid.getUserId(), bid.getItemId());
//
//            Log.w(BidDao.class.getSimpleName(), "Bid will be updated: (" + bid.getId() + ", " + bid.getValue() + ")");
//
//            bid.setId(alreadyExists.getId());
//            return update(bid);
//        }catch(IllegalArgumentException e){
//            Log.w(BidDao.class.getSimpleName(), "Bid will be created: (" + bid.getId() + ", " + bid.getValue() + ")");
//        }

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(AuctionDbSchema.BidsTable.COLUMN_USER_ID, bid.getUserId());
        values.put(AuctionDbSchema.BidsTable.COLUMN_ITEM_ID, bid.getItemId());
        values.put(AuctionDbSchema.BidsTable.COLUMN_CREATION_DATE, bid.getCreatedAt());
        values.put(AuctionDbSchema.BidsTable.COLUMN_VALUE, bid.getValue());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = getDatabase().insert(AuctionDbSchema.BidsTable.TABLE_NAME, null, values);

        if(newRowId == -1){
            throw new Exception("Error inserting the bid.");
        }

        bid.setId(newRowId);

        Log.w(BidDao.class.getSimpleName(), "Bid created: (" + bid.getId() + ", " + bid.getValue() + ")");
        return bid;
    }

    /**
     * Gets a bid.
     * @param userId User id.
     * @param itemId Item id.
     * @return Bid.
     * @throws Exception
     */
    public Bid getBid(long userId, long itemId) throws Exception{
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                AuctionDbSchema.BidsTable._ID,
                AuctionDbSchema.BidsTable.COLUMN_USER_ID,
                AuctionDbSchema.BidsTable.COLUMN_ITEM_ID,
                AuctionDbSchema.BidsTable.COLUMN_VALUE,
                AuctionDbSchema.BidsTable.COLUMN_CREATION_DATE
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = AuctionDbSchema.BidsTable.COLUMN_USER_ID + " = ? AND " + AuctionDbSchema.BidsTable.COLUMN_ITEM_ID + "= ?";
        String[] selectionArgs = { String.valueOf(userId), String.valueOf(itemId) };

        Cursor cursor = getDatabase().query(
                AuctionDbSchema.BidsTable.TABLE_NAME,      // The table to query
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
    public Bid update(Bid bid) throws Exception {
        // New value for one column
        ContentValues values = new ContentValues();
        values.put(AuctionDbSchema.BidsTable.COLUMN_CREATION_DATE, bid.getCreatedAt());
        values.put(AuctionDbSchema.BidsTable.COLUMN_VALUE, bid.getValue());

        // Which row to update, based on the title
        String selection = AuctionDbSchema.BidsTable.COLUMN_USER_ID + " = ? AND " + AuctionDbSchema.BidsTable.COLUMN_ITEM_ID + " = ? ";
        String[] selectionArgs = { String.valueOf(bid.getUserId()), String.valueOf(bid.getItemId()) };

        int count = getDatabase().update(
                AuctionDbSchema.BidsTable.TABLE_NAME,
                values,
                selection,
                selectionArgs);

        if(count <= 0){
            throw new Exception("Error updating the bid.");
        }

        return bid;
    }

    @Override
    public Bid get(long id) throws Exception {
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                AuctionDbSchema.BidsTable._ID,
                AuctionDbSchema.BidsTable.COLUMN_USER_ID,
                AuctionDbSchema.BidsTable.COLUMN_ITEM_ID,
                AuctionDbSchema.BidsTable.COLUMN_VALUE,
                AuctionDbSchema.BidsTable.COLUMN_CREATION_DATE
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = AuctionDbSchema.BidsTable._ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };

        Cursor cursor = getDatabase().query(
                AuctionDbSchema.BidsTable.TABLE_NAME,      // The table to query
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
    public boolean remove(long id) throws Exception {
        // Define 'where' part of query.
        String selection = AuctionDbSchema.BidsTable._ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { String.valueOf(id) };

        // Issue SQL statement.
        int count = getDatabase().delete(AuctionDbSchema.BidsTable.TABLE_NAME, selection, selectionArgs);
        return count > 0;
    }

    @Override
    public Bid parse(Cursor cursor) throws Exception {
        return staticParse(cursor);
    }

    /**
     * Static parses a cursor to bid.
     * @param cursor Cursor.
     * @return Bid.
     * @throws Exception
     */
    public static Bid staticParse(Cursor cursor) throws Exception {
        Bid bid = new Bid();
        bid.setId(
                cursor.getLong(cursor.getColumnIndexOrThrow(AuctionDbSchema.BidsTable._ID))
        );

        bid.setUserId(
                cursor.getLong(cursor.getColumnIndexOrThrow(AuctionDbSchema.BidsTable.COLUMN_USER_ID))
        );

        bid.setItemId(
                cursor.getLong(cursor.getColumnIndexOrThrow(AuctionDbSchema.BidsTable.COLUMN_ITEM_ID))
        );

        bid.setValue(
                cursor.getFloat(cursor.getColumnIndexOrThrow(AuctionDbSchema.BidsTable.COLUMN_VALUE))
        );

        bid.setCreatedAt(
                cursor.getLong(cursor.getColumnIndexOrThrow(AuctionDbSchema.BidsTable.COLUMN_CREATION_DATE))
        );
        return bid;
    }

    @Override
    public ArrayList<Bid> getAll() throws Exception {
        Cursor cursor = getDatabase().rawQuery("SELECT * FROM " + AuctionDbSchema.BidsTable.TABLE_NAME, null);
        ArrayList<Bid> result = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Bid item = parse(cursor);
                result.add(item);
                cursor.moveToNext();
            }
        }
        return result;
    }
}
