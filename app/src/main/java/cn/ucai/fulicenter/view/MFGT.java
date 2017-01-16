package cn.ucai.fulicenter.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import cn.ucai.fulicenter.MainActivity;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.controller.activity.BoutiquechildActivity;
import cn.ucai.fulicenter.controller.activity.CategoryChildActivity;
import cn.ucai.fulicenter.controller.activity.GoodsDetailsActivity;
import cn.ucai.fulicenter.controller.activity.SplashActivity;
import cn.ucai.fulicenter.model.bean.BoutiqueBean;
import cn.ucai.fulicenter.model.bean.CategoryChildBean;

/**
 * Created by MTJ on 2017/1/10.
 */

public class MFGT {
    public static void finish(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    public static void startActivity(Activity context, Class<?> clz) {
        context.overridePendingTransition(R.anim.push_right_in, R.anim.push_bottom_out);
        context.startActivity(new Intent(context, clz));

    }

    public static void startActivity(Activity context, Intent intent) {
        context.overridePendingTransition(R.anim.push_right_in, R.anim.push_bottom_out);
        context.startActivity(intent);
    }

    //精品第一界面跳转
    public static void gotoBoutiqueChild(Context context, BoutiqueBean boutiqueBean) {
        Intent intent = new Intent(context, BoutiquechildActivity.class);
        intent.putExtra(I.NewAndBoutiqueGoods.CAT_ID, boutiqueBean.getId());
        intent.putExtra(I.Boutique.NAME, boutiqueBean.getTitle());
        startActivity((Activity) context, intent);
    }

    //新品一界面点击跳转
    public static void gotoGoodsDetail(Context context, int goodsId) {
        Intent intent = new Intent(context, GoodsDetailsActivity.class);
        intent.putExtra(I.GoodsDetails.KEY_GOODS_ID, goodsId);
        startActivity((Activity) context, intent);
    }
    //分类1跳二
    public static void gotoCategoryChild(Context context, int catId, String getName, ArrayList<CategoryChildBean> list) {
        Intent intent = new Intent(context,CategoryChildActivity.class);
        intent.putExtra(I.NewAndBoutiqueGoods.CAT_ID, catId);
        intent.putExtra(I.CategoryGroup.NAME,getName);
        intent.putExtra(I.CategoryChild.DATA,list);
        startActivity((Activity) context, intent);
    }
}
