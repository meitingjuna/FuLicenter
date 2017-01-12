package cn.ucai.fulicenter.model.net;

import android.content.Context;

import cn.ucai.fulicenter.model.bean.BoutiqueBean;
import cn.ucai.fulicenter.model.bean.GoodsDetailsBean;

/**
 * Created by MTJ on 2017/1/11.
 */

public interface IModelGoods {
    void downData(Context context,int goodsId, OnCompleteListener<GoodsDetailsBean> listener);
}
