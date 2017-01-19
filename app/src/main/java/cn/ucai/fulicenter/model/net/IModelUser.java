package cn.ucai.fulicenter.model.net;

import android.content.Context;

import java.io.File;

import cn.ucai.fulicenter.model.bean.CartBean;
import cn.ucai.fulicenter.model.bean.CollectBean;
import cn.ucai.fulicenter.model.bean.MessageBean;
import cn.ucai.fulicenter.model.bean.NewGoodsBean;

/**
 * Created by MTJ on 2017/1/16.
 */

public interface IModelUser {
    void login(Context context, String Username, String password, OnCompleteListener<String> listener);
    void register(Context context, String username, String usernick, String password, OnCompleteListener<String> listener);
    void updatNick(Context context, String username, String usernick, OnCompleteListener<String> listener);
    void updatAvatar(Context context, String username, File file, OnCompleteListener<String> listener);
    void collectCount(Context context, String username, OnCompleteListener<MessageBean> listener);
    void getCollects(Context context, String username, int pageId, int pageSize, OnCompleteListener<CollectBean[]> lister);
    void getCart(Context context , String username, OnCompleteListener<CartBean[]>listener);
    /*void addCart(Context context , String username,int goodsId,int cunt, OnCompleteListener<MessageBean>listener);
    void delCart(Context context ,int cartId, OnCompleteListener<MessageBean>listener);
    void updateCart(Context context ,int cartId,int cunt, OnCompleteListener<MessageBean>listener);*/
    void updateCart(Context context,int action,String username,int goodsId,int cunt,int cartId,OnCompleteListener<MessageBean>listener);
}