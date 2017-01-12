package cn.ucai.fulicenter.controller.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.model.bean.GoodsDetailsBean;
import cn.ucai.fulicenter.model.net.IModelGoods;
import cn.ucai.fulicenter.model.net.ModelGoods;
import cn.ucai.fulicenter.model.net.OnCompleteListener;
import cn.ucai.fulicenter.view.MFGT;

public class GoodsDetailsActivity extends AppCompatActivity {
    int goodsId = 0;
    IModelGoods model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);
        goodsId = getIntent().getIntExtra(I.GoodsDetails.KEY_GOODS_ID, 0);
        if (goodsId == 0) {
            MFGT.finish(this);
        } else {
            initView();
            initData();
        }
    }

    private void initData() {
        model = new ModelGoods();
        model.downData(this, goodsId, new OnCompleteListener<GoodsDetailsBean>() {
            @Override
            public void onSuccess(GoodsDetailsBean result) {
                if (result!=null){
                    showGoodsDetail(result);
                }
                
            }

            @Override
            public void onError(String error) {

            }
        });

    }

    private void showGoodsDetail(GoodsDetailsBean goods) {
    }

    private void initView() {

    }
}
