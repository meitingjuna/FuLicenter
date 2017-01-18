package cn.ucai.fulicenter.model.net;

import android.content.Context;
import android.content.SharedPreferences;

import cn.ucai.fulicenter.application.I;

/**
 * Created by MTJ on 2017/1/16.
 */
public class SharePrefrenceUtils {
    private static final String SHARE_PREFERENCE_NAME = "cn.user.fulicenter_user";
    private static final String SHARE_PREFERENCE_NAME_USERNAME = "cn.fulicenter_user_username";
    private static SharePrefrenceUtils instsance;
    private SharedPreferences.Editor mEditor;
    private static SharedPreferences preferences;

    public SharePrefrenceUtils(Context context) {
        preferences = context.getSharedPreferences(SHARE_PREFERENCE_NAME, Context.MODE_PRIVATE);
        mEditor = preferences.edit();
    }

    public static SharePrefrenceUtils getInstance(Context context) {
        if (instsance == null) {
            instsance = new SharePrefrenceUtils(context);
        }
        return instsance;
    }

    public void saveUser(String username) {
        mEditor.putString(SHARE_PREFERENCE_NAME_USERNAME, username).commit();
        mEditor.commit();
    }

    public String getUser() {
        return preferences.getString(SHARE_PREFERENCE_NAME_USERNAME, null);
    }

    public void removeUser() {
        mEditor.remove(SHARE_PREFERENCE_NAME_USERNAME);
        mEditor.commit();
    }
}
