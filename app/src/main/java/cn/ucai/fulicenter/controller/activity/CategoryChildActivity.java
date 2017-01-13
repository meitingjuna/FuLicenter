package cn.ucai.fulicenter.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.controller.fragment.NewGoodsFragment;
import cn.ucai.fulicenter.view.MFGT;

public class CategoryChildActivity extends AppCompatActivity {
    NewGoodsFragment mNewGoodFragment;
    boolean pricAsc = false;
    boolean addTimeAsc = false;
    @BindView(R.id.tv_common_title)
    TextView tvCommonTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_child);
        ButterKnife.bind(this);
        mNewGoodFragment = new NewGoodsFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mNewGoodFragment)
                .commit();
    }


    @OnClick({R.id.btnPriceSort, R.id.btnAddTimeSort})
    public void onClick(View view) {
        int sorBy = I.SORT_BY_ADDTIME_ASC;
        switch (view.getId()) {
            case R.id.btnPriceSort:
                if (pricAsc) {
                    sorBy = I.SORT_BY_PRICE_ASC;
                } else {
                    sorBy = I.SORT_BY_PRICE_DESC;
                }
                break;
            case R.id.btnAddTimeSort:
                if (addTimeAsc) {
                    sorBy = I.SORT_BY_ADDTIME_ASC;
                } else {
                    sorBy = I.SORT_BY_ADDTIME_DESC;
                }
                break;
        }
        mNewGoodFragment.sortGoods(sorBy);
    }

    @OnClick(R.id.ivBack)
    public void onClick() {
        MFGT.finish(this);
    }
}
