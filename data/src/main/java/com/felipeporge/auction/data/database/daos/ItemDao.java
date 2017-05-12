package com.felipeporge.auction.data.database.daos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.felipeporge.auction.data.database.daos.base.DataAccessObject;
import com.felipeporge.auction.data.database.schema.AuctionDbSchema;
import com.felipeporge.auction.data.entities.Bid;
import com.felipeporge.auction.data.entities.Item;

import java.util.ArrayList;

/**
 * This class represents the item data acess object.
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
        values.put(AuctionDbSchema.ItemsTable.COLUMN_TITLE, item.getTitle());
        values.put(AuctionDbSchema.ItemsTable.COLUMN_DESCRIPTION, item.getDescription());
        values.put(AuctionDbSchema.ItemsTable.COLUMN_IMG, item.getImg());
        values.put(AuctionDbSchema.ItemsTable.COLUMN_END_DATE, item.getFinishAt());
        values.put(AuctionDbSchema.ItemsTable.COLUMN_STARTING_BID, item.getStartingBid());
        values.put(AuctionDbSchema.ItemsTable.COLUMN_USER_ID, item.getUserId());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = getDatabase().insert(AuctionDbSchema.ItemsTable.TABLE_NAME, null, values);

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
        values.put(AuctionDbSchema.ItemsTable.COLUMN_TITLE, item.getTitle());
        values.put(AuctionDbSchema.ItemsTable.COLUMN_DESCRIPTION, item.getDescription());
        values.put(AuctionDbSchema.ItemsTable.COLUMN_IMG, item.getImg());
        values.put(AuctionDbSchema.ItemsTable.COLUMN_END_DATE, item.getFinishAt());
        values.put(AuctionDbSchema.ItemsTable.COLUMN_STARTING_BID, item.getStartingBid());
        values.put(AuctionDbSchema.ItemsTable.COLUMN_USER_ID, item.getUserId());

        // Which row to update, based on the title
        String selection = AuctionDbSchema.ItemsTable._ID + " = ?";
        String[] selectionArgs = { String.valueOf(item.getId()) };

        int count = getDatabase().update(
                AuctionDbSchema.ItemsTable.TABLE_NAME,
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
                AuctionDbSchema.ItemsTable._ID,
                AuctionDbSchema.ItemsTable.COLUMN_TITLE,
                AuctionDbSchema.ItemsTable.COLUMN_DESCRIPTION,
                AuctionDbSchema.ItemsTable.COLUMN_IMG,
                AuctionDbSchema.ItemsTable.COLUMN_END_DATE,
                AuctionDbSchema.ItemsTable.COLUMN_STARTING_BID,
                AuctionDbSchema.ItemsTable.COLUMN_USER_ID
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = AuctionDbSchema.ItemsTable._ID + " = ?";
        String[] selectionArgs = { String.valueOf(itemId) };

        Cursor cursor = getDatabase().query(
                AuctionDbSchema.ItemsTable.TABLE_NAME,     // The table to query
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


    /**
     * Gets current item bid value.
     * @param itemId Item id.
     * @return Current bid value.
     */
    public Bid getCurrentBid(long itemId, long endDate) throws Exception {
        String sqlQuery =
                "SELECT * FROM " + AuctionDbSchema.BidsTable.TABLE_NAME + " WHERE " +
                AuctionDbSchema.BidsTable.COLUMN_ITEM_ID + " = ? AND " +
                AuctionDbSchema.BidsTable.COLUMN_CREATION_DATE + " <= ? ORDER BY " +
                        AuctionDbSchema.BidsTable.COLUMN_VALUE + " DESC LIMIT 1";


        Cursor cursorValue = getDatabase().rawQuery(sqlQuery,
                new String[]{ String.valueOf(itemId), String.valueOf(endDate) });

        if(!cursorValue.moveToFirst()){
            return null;
        }
        return BidDao.staticParse(cursorValue);
    }

    /**
     * Get item bid count.
     * @param itemId Item id.
     * @return Bid count.
     */
    public int getBidCount(long itemId, long endDate){
        Cursor cursorValue = getDatabase().rawQuery("SELECT COUNT(*) FROM " +
                        AuctionDbSchema.BidsTable.TABLE_NAME + " WHERE " +
                        AuctionDbSchema.BidsTable.COLUMN_ITEM_ID + " = ? AND " +
                        AuctionDbSchema.BidsTable.COLUMN_CREATION_DATE + " <= ?",
                new String[]{ String.valueOf(itemId), String.valueOf(endDate) });

        if(!cursorValue.moveToFirst()){
            return 0;
        }

        return cursorValue.getInt(0);
    }

    @Override
    public boolean remove(long id) throws Exception {
        // Define 'where' part of query.
        String selection = AuctionDbSchema.ItemsTable._ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { String.valueOf(id) };

        // Issue SQL statement.
        int count = getDatabase().delete(AuctionDbSchema.ItemsTable.TABLE_NAME, selection, selectionArgs);
        return count > 0;
    }

    @Override
    public Item parse(Cursor cursor) throws Exception {
        Item item = new Item();

        long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(AuctionDbSchema.ItemsTable._ID));
        long endDate = cursor.getLong(cursor.getColumnIndexOrThrow(AuctionDbSchema.ItemsTable.COLUMN_END_DATE));

        item.setId(itemId);
        item.setFinishAt(endDate);

        item.setTitle(
                cursor.getString(cursor.getColumnIndexOrThrow(AuctionDbSchema.ItemsTable.COLUMN_TITLE))
        );

        item.setDescription(
                cursor.getString(cursor.getColumnIndexOrThrow(AuctionDbSchema.ItemsTable.COLUMN_DESCRIPTION))
        );

        item.setImg(
                cursor.getString(cursor.getColumnIndexOrThrow(AuctionDbSchema.ItemsTable.COLUMN_IMG))
        );


        item.setStartingBid(
                cursor.getFloat(cursor.getColumnIndexOrThrow(AuctionDbSchema.ItemsTable.COLUMN_STARTING_BID))
        );

        item.setUserId(
                cursor.getLong(cursor.getColumnIndexOrThrow(AuctionDbSchema.ItemsTable.COLUMN_USER_ID))
        );

        item.setCurrentBid(getCurrentBid(itemId, endDate));
        item.setBidCount(getBidCount(itemId, endDate));

        return item;
    }

    @Override
    public ArrayList<Item> getAll() throws Exception {
        Cursor cursor = getDatabase().rawQuery("SELECT * FROM " + AuctionDbSchema.ItemsTable.TABLE_NAME, null);
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



    /**
     * Get all user items.
     * @param userId User id.
     * @return Array of items.
     */
    public ArrayList<Item> getAllUserItems(long userId) throws Exception {
        Cursor cursor = getDatabase().rawQuery(
                "SELECT * FROM " + AuctionDbSchema.ItemsTable.TABLE_NAME +
                        " WHERE " + AuctionDbSchema.ItemsTable.COLUMN_USER_ID + " = ?" +
                        " ORDER BY " + AuctionDbSchema.ItemsTable.COLUMN_END_DATE + " ASC",
                new String[]{ String.valueOf(userId)}
        );

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

    /**
     * Get all items enabled to receive bids.
     * @return Array of items.
     */
    public ArrayList<Item> getAllItemsInAuction(long userId) throws Exception {
        long currentMillis = System.currentTimeMillis();

        Cursor cursor = getDatabase().rawQuery(
                "SELECT * FROM " + AuctionDbSchema.ItemsTable.TABLE_NAME +
                        " WHERE " + AuctionDbSchema.ItemsTable.COLUMN_END_DATE + " > ?" +
//                        " AND " + AuctionDbSchema.ItemsTable.COLUMN_USER_ID + " <> ?" +
                        " ORDER BY " + AuctionDbSchema.ItemsTable.COLUMN_END_DATE + " ASC",
                new String[]{
                        String.valueOf(currentMillis)
//                        ,String.valueOf(userId)
                }
        );

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

    /**
     * Get all items enabled to receive bids.
     * @return Array of items.
     */
    public ArrayList<Item> getItemsActiveForBid(long userId) throws Exception {
        long currentMillis = System.currentTimeMillis();

        Cursor cursor = getDatabase().rawQuery(
                "SELECT * FROM " + AuctionDbSchema.ItemsTable.TABLE_NAME +
                        " WHERE " + AuctionDbSchema.ItemsTable.COLUMN_END_DATE + " > ?" +
                        " AND " + AuctionDbSchema.ItemsTable.COLUMN_USER_ID + " <> ?" +
                        " ORDER BY " + AuctionDbSchema.ItemsTable.COLUMN_END_DATE + " ASC",
                new String[]{
                        String.valueOf(currentMillis), String.valueOf(userId)
                }
        );

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

    /**
     * Gets all user bidding items.
     * @param userId User id.
     * @return Bidding items
     */
    public ArrayList<Item> getAllUserBiddingItems(long userId) throws Exception {
        long currMillis = System.currentTimeMillis();

        String querySql = "SELECT " + AuctionDbSchema.ItemsTable.TABLE_NAME + ".* FROM " + AuctionDbSchema.ItemsTable.TABLE_NAME +
                " WHERE " + AuctionDbSchema.ItemsTable.TABLE_NAME + "." + AuctionDbSchema.ItemsTable.COLUMN_END_DATE + " > ? AND " +
                AuctionDbSchema.ItemsTable.TABLE_NAME + "." + AuctionDbSchema.ItemsTable._ID + " IN ( SELECT " +
                AuctionDbSchema.BidsTable.TABLE_NAME + "." + AuctionDbSchema.BidsTable.COLUMN_ITEM_ID +" FROM " +
                AuctionDbSchema.BidsTable.TABLE_NAME + " WHERE " +
                AuctionDbSchema.BidsTable.TABLE_NAME + "." + AuctionDbSchema.BidsTable.COLUMN_USER_ID + " = ? )" +
                " ORDER BY " + AuctionDbSchema.ItemsTable.COLUMN_END_DATE + " ASC";

        Cursor cursor = getDatabase().rawQuery(querySql,
                new String[]{ String.valueOf(currMillis), String.valueOf(userId)});

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

    /**
     * Gets all user won items.
     * @param userId User id.
     * @return Won items
     */
    public ArrayList<Item> getAllUserWonItems(long userId) throws Exception {

        long currMillis = System.currentTimeMillis();

        String querySql =
                "SELECT i1.* FROM items i1" +
                        " INNER JOIN bids b1 ON b1.item_id = i1._id AND b1.user_id = ? AND b1.value = (" +
                            " SELECT MAX(b2.value) FROM bids b2 WHERE b2.item_id = i1._id AND b2.user_id = ?" +
                        ") WHERE i1.finish_at < ?";

        Cursor cursor = getDatabase().rawQuery(querySql,
                new String[]{
                        String.valueOf(userId),
                        String.valueOf(userId),
                        String.valueOf(currMillis)
                });

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

    /**
     * Search for items.
     * @param keyword Search keyword.
     * @return Array of items.
     */
    public ArrayList<Item> searchItems(String keyword) throws Exception {
        Cursor cursor = getDatabase().rawQuery("SELECT * FROM " + AuctionDbSchema.ItemsTable.TABLE_NAME +
                " WHERE " + AuctionDbSchema.ItemsTable.COLUMN_TITLE +
                " LIKE %" + keyword + "%", null);

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
