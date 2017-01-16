package cn.ucai.fulicenter.controller.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.controller.fragment.NewGoodsFragment;
import cn.ucai.fulicenter.model.bean.CategoryChildBean;
import cn.ucai.fulicenter.view.CatFilterButton;
import cn.ucai.fulicenter.view.MFGT;

public class CategoryChildActivity extends AppCompatActivity {
    NewGoodsFragment mNewGoodFragment;
    boolean pricAsc = false;
    boolean addTimeAsc = false;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.btnPriceSort)
    Button btnPriceSort;
    @BindView(R.id.btnAddTimeSort)
    Button btnAddTimeSort;
    @BindView(R.id.mButton_file)
    CatFilterButton mButtonFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_child);
        ButterKnife.bind(this);
        mNewGoodFragment = new NewGoodsFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mNewGoodFragment)
                .commit();
        tvTitle.setText(getIntent().getStringExtra(I.CategoryGroup.NAME));
        ArrayList<CategoryChildBean> list = (ArrayList<CategoryChildBean>) getIntent().getSerializableExtra(I.CategoryChild.DATA);
        String groupName = getIntent().getStringExtra(I.CategoryGroup.NAME);
        mButtonFile.initCatFileterButton(groupName,list);
    }


    @OnClick({R.id.btnPriceSort, R.id.btnAddTimeSort})
    public void onClick(View view) {
        int sorBy = I.SORT_BY_ADDTIME_ASC;
        Drawable right;
        switch (view.getId()) {
            case R.id.btnPriceSort:
                if (pricAsc) {
                    sorBy = I.SORT_BY_PRICE_ASC;
                    right = getResources().getDrawable(R.drawable.arrow_order_up);
                } else {
                    sorBy = I.SORT_BY_PRICE_DESC;
                    right = getResources().getDrawable(R.drawable.arrow_order_down);

                }
                right.setBounds(0, 0, right.getIntrinsicWidth(), right.getIntrinsicHeight());
                btnPriceSort.setCompoundDrawablesWithIntrinsicBounds(null, null, right, null);
                pricAsc = !pricAsc;
                break;
            case R.id.btnAddTimeSort:
                if (addTimeAsc) {
                    sorBy = I.SORT_BY_ADDTIME_ASC;
                    right = getResources().getDrawable(R.drawable.arrow_order_up);
                } else {
                    sorBy = I.SORT_BY_ADDTIME_DESC;
                    right = getResources().getDrawable(R.drawable.arrow_order_down);
                }
                right.setBounds(0, 0, right.getIntrinsicWidth(), right.getIntrinsicHeight());
                btnAddTimeSort.setCompoundDrawablesWithIntrinsicBounds(null, null, right, null);
                addTimeAsc = !addTimeAsc;
                break;
        }
        mNewGoodFragment.sortGoods(sorBy);
    }

    @OnClick(R.id.mBack)
    public void onClick() {
        MFGT.finish(this);
    }

}
