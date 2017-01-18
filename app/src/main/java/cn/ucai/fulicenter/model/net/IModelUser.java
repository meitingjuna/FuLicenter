package cn.ucai.fulicenter.model.net;

import android.content.Context;

/**
 * Created by MTJ on 2017/1/16.
 */

public interface IModelUser {
    void login(Context context,String Username,String password,OnCompleteListener<String> listener);
    void register(Context context,String username,String usernick,String password,OnCompleteListener<String>listener);
    void updatNick(Context context,String username,String usernick,OnCompleteListener<String>listener);
}
