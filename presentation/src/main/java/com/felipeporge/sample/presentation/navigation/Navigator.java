package com.felipeporge.sample.presentation.navigation;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import com.felipeporge.sample.presentation.model.SampleItemModel;
import com.felipeporge.sample.presentation.views.activities.ItemDetailsActivity;
import com.felipeporge.sample.presentation.views.activities.LoginActivity;
import com.felipeporge.sample.presentation.views.activities.MainActivity;
import com.felipeporge.sample.presentation.views.activities.WalkthroughActivity;

/**
 * This class controls the app navigation.
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 22/11/16
 */
public class Navigator {

    /**
     * Navigates to {@link LoginActivity} screen.
     * @param context - The current {@link Context}.
     * @param animate - False to disable transition animation.
     * @return - False if there is no intent to resolve it.
     */
    public static boolean navigateToLoginScreen(Context context, boolean animate){
        Intent intent = new Intent(context, LoginActivity.class);
        if(!animate) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        }
        return fireIntent(context, intent);
    }

    /**
     * Navigates to {@link WalkthroughActivity}.
     * @param context The current {@link Context}.
     * @return - False if there is no intent to resolve it.
     */
    public static boolean navigateToWalkthrough(Context context) {
        return fireIntent(context, new Intent(context, WalkthroughActivity.class));
    }

    /**
     * Navigates to {@link MainActivity}.
     * @param context The current {@link Context}.
     * @return - False if there is no intent to resolve it.
     */
    public static boolean navigateToMainScreen(Context context) {
        return fireIntent(context, MainActivity.getCallingIntent(context));
    }

    /**
     * Navigates to {@link ItemDetailsActivity}.
     * @param context The current {@link Context}.
     * @param itemModel Item to show.
     * @return - False if there is no intent to resolve it.
     */
    public static boolean navigateToItemDetails(Context context,SampleItemModel itemModel){
        return fireIntent(context, ItemDetailsActivity.getCallingIntent(context, itemModel));
    }

    /**
     * Opens an URL.
     * @param url - Url to open.
     * @return - False if there is no intent to resolve it.
     */
    public static boolean openUrl(Context context, String url) {
        return openUrl(context, Uri.parse(url));
    }

    /**
     * Opens an URL.
     * @param uri - Url to open.
     * @return - False if there is no intent to resolve it.
     */
    public static boolean openUrl(Context context, Uri uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        return fireIntent(context, intent);
    }

    /**
     * Fires an intent.
     * @param context - The {@link Context}.
     * @param intent - The {@link Intent} to fire.
     * @return {@link Boolean} True (successfully fired) or false (otherwise).
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private static boolean fireIntent(Context context, Intent intent){
        if (intent.resolveActivity(context.getPackageManager()) != null) {

            boolean isActivity = context instanceof Activity;
            boolean isCompatible = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
            boolean isAllowingAnimations = intent.getFlags() != Intent.FLAG_ACTIVITY_NO_ANIMATION;

            if(!isActivity || !isCompatible || !isAllowingAnimations) {
                context.startActivity(intent);
            } else  {
                context.startActivity(intent,
                        ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle()
                );
            }
            return true;
        }
        return false;
    }
}