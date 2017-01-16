package cn.ucai.fulicenter.model.net;

import android.content.Context;
import android.content.SharedPreferences;

import cn.ucai.fulicenter.application.I;

/**
 * Created by MTJ on 2017/1/16.
 */

public class SharePrefrenceUtils {
    private static final String SHARE_PREFENCE_NAME = "cn.user.fulicenter_user";
    private static final String SHARE_PREFENCE_USERNAME = "cn.user.fulicenter_user_username";
    private static SharePrefrenceUtils instance;
    private static SharedPreferences preferences;

    public SharePrefrenceUtils(Context context) {
        preferences = context.getSharedPreferences(SHARE_PREFENCE_NAME, Context.MODE_PRIVATE);
    }

    public static SharePrefrenceUtils getInstance(Context context) {
        if (instance == null) {
            instance = new SharePrefrenceUtils(context);
        }
        return instance;
    }

    public static void saveUser(String username) {
        preferences.edit().putString(SHARE_PREFENCE_USERNAME, username).commit();
    }

    public static String getUser() {
        return preferences.getString(SHARE_PREFENCE_NAME, null);
    }
}
