package com.felipeporge.auction.presentation.helpers;

import android.content.Context;

/**
 * This class stores some resource util methods.
 */
public class ResourceHelper {

    /**
     * Returns the int id of a specific resource.
     * E.G.: getResourceId("myIcon", "drawable", getPackageName()) to R.drawable.myIcon.
     *
     * @param context - The context.
     * @param resourceName - Resource name.
     * @param resourceType - Resource type.
     * @return - Resource int id.
     */
    public static int getResourceId(Context context, String resourceName, String resourceType) {
        try {
            return context.getResources().getIdentifier(resourceName, resourceType, context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
