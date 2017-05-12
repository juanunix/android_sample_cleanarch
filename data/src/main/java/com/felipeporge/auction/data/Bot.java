package com.felipeporge.auction.data;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.felipeporge.auction.data.config.Constants;
import com.felipeporge.auction.data.database.daos.BidDao;
import com.felipeporge.auction.data.database.daos.ItemDao;
import com.felipeporge.auction.data.entities.Bid;
import com.felipeporge.auction.data.entities.Item;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class represents a simple auction bot.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 24/11/2016
 */
public class Bot {

    private static final Random RANDOM = new Random();
    private static final int BOT_ID = 1;
    private static final boolean ENABLE_BOT = true;

    private static long sLastBotBidMillis = -1;

    private static ItemDao sItemDao;
    private static BidDao sBidDao;

    /**
     * Places a new bid.
     * @param writableDatabase - Writable database instance.
     */
    public static void placeNewBid(SQLiteDatabase writableDatabase){

        if(!ENABLE_BOT)
            return;

        long currTime = System.currentTimeMillis();
        if(sLastBotBidMillis != -1 && (currTime - sLastBotBidMillis) > Constants.Time.MINUTE_MS){
            Log.w(Bot.class.getSimpleName(), "I will place a new bid soon, but not now...");
            return;
        }

        if(sItemDao == null)
            sItemDao = new ItemDao(writableDatabase);

        if(sBidDao == null)
            sBidDao = new BidDao(writableDatabase);

        try {
            ArrayList<Item> items = sItemDao.getItemsActiveForBid(BOT_ID);
            if(items.size() == 0){
                Log.w(Bot.class.getSimpleName(), "There is no active items to place a bid :(");
                return;
            }

            int randomPosition = (int) Math.ceil(RANDOM.nextFloat() * (items.size() - 1));
            Item randomItem = items.get(randomPosition);

            float currentBidValue = randomItem.getCurrentBidValue();

            // Random bid value = current bid value plus from 1 to 5% of the current bid value.
            float randomBidValue = ((0.1f + (RANDOM.nextFloat() * 4))/10 * currentBidValue) + currentBidValue;

            Bid randomBid = new Bid();
            randomBid.setItemId(randomItem.getId());
            randomBid.setUserId(BOT_ID);
            randomBid.setCreatedAt(currTime);
            randomBid.setValue(randomBidValue);
            sBidDao.create(randomBid);

            sLastBotBidMillis = currTime;
            Log.w(Bot.class.getSimpleName(),
                    "I placed a new bid: { item.title = " + randomItem.getTitle() +
                    ", bid.value = " + randomBid.getValue() + "}");
        }catch(Exception e){
            Log.w(Bot.class.getSimpleName(), "An error occurred when I tried to place a new bid.");
            e.printStackTrace();
        }
    }
}
