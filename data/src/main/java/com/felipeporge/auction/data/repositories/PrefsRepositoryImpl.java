package com.felipeporge.auction.data.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import com.felipeporge.auction.domain.repositories.PrefsRepository;
import com.felipeporge.auction.domain.repositories.base.RepositoryCallback;

/**
 * This class represents the shared preferences repository.
 *
 * @author Felipe Porge Xavier - <a href="http://www.felipeporge.com/">www.felipeporge.com</a>
 * @since 23/11/2016
 */
public class PrefsRepositoryImpl implements PrefsRepository {

    private static final String PREFS = "prfs";
    private static final String SHOWED_WALKTHROUGH_KEY = "shwdWalk";

    private SharedPreferences mSharedPrefs;

    /**
     * Constructor method.
     * @param context The context.
     */
    public PrefsRepositoryImpl(Context context){
        mSharedPrefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    @Override
    public void getShowedWalkthrough(RepositoryCallback<Boolean> callback) {
        boolean result = mSharedPrefs.getBoolean(SHOWED_WALKTHROUGH_KEY, false);
        callback.onRepoSuccess(result);
    }

    @Override
    public void putShowedWalkthrough(boolean showedWalkthrough, RepositoryCallback<Void> callback) {
        mSharedPrefs.edit().putBoolean(SHOWED_WALKTHROUGH_KEY, showedWalkthrough).apply();
        callback.onRepoSuccess(null);
    }
}
