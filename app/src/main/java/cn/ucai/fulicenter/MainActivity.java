package cn.ucai.fulicenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ucai.fulicenter.application.FuLiCenterApplication;
import cn.ucai.fulicenter.application.I;
import cn.ucai.fulicenter.controller.fragment.BoutiqueFragment;
import cn.ucai.fulicenter.controller.fragment.CategoryFragment;
import cn.ucai.fulicenter.controller.fragment.NewGoodsFragment;
import cn.ucai.fulicenter.controller.fragment.PersonalCenteFragment;
import cn.ucai.fulicenter.view.MFGT;

public class MainActivity extends AppCompatActivity {
    int index, currentIndex;
    Fragment[] mFragment = new Fragment[5];
    RadioButton[] rbs = new RadioButton[5];
    @BindView(R.id.Lauout_newgoods)
    RadioButton LauoutNewgoods;
    @BindView(R.id.Layout_boutigue)
    RadioButton LayoutBoutigue;
    @BindView(R.id.Layout_category)
    RadioButton LayoutCategory;
    @BindView(R.id.Layout_cart)
    RadioButton LayoutCart;
    @BindView(R.id.Layout_personal)
    RadioButton LayoutPersonal;

    NewGoodsFragment mNewGoodsFragment;
    BoutiqueFragment mBoutiqueGoodsFragment;
    CategoryFragment mCategoryFragment;
    PersonalCenteFragment mPersonalCenteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        rbs[0] = LauoutNewgoods;
        rbs[1] = LayoutBoutigue;
        rbs[2] = LayoutCategory;
        rbs[3] = LayoutCart;
        rbs[4] = LayoutPersonal;

        mNewGoodsFragment = new NewGoodsFragment();
        mBoutiqueGoodsFragment = new BoutiqueFragment();
        mCategoryFragment = new CategoryFragment();
        mPersonalCenteFragment = new PersonalCenteFragment();

        mFragment[0] = mNewGoodsFragment;
        mFragment[1] = mBoutiqueGoodsFragment;
        mFragment[2] = mCategoryFragment;
        mFragment[4] = mPersonalCenteFragment;

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mNewGoodsFragment)
                .add(R.id.fragment_container, mBoutiqueGoodsFragment)
                .add(R.id.fragment_container, mCategoryFragment)
                // .add(R.id.fragment_container, mPersonalCenteFragment)
                .show(mNewGoodsFragment)
                .hide(mBoutiqueGoodsFragment)
                .hide(mCategoryFragment)
                //  .hide(mPersonalCenteFragment)
                .commit();
    }

    public void onCheckedChange(View view) {
        switch (view.getId()) {
            case R.id.Lauout_newgoods:
                index = 0;
                break;
            case R.id.Layout_boutigue:
                index = 1;
                break;
            case R.id.Layout_category:
                index = 2;
                break;
            case R.id.Layout_cart:
                index = 3;
                break;
            case R.id.Layout_personal:
                if (FuLiCenterApplication.getUser() == null) {
                    MFGT.gotoLogin(this);
                } else {
                    index = 4;
                }
                break;
        }
        setFragment();
        if (index != currentIndex) {
            setRadioStatus();
        }
    }

    private void setFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.hide(mFragment[currentIndex]);
        if (!mFragment[index].isAdded()) {
            ft.add(R.id.fragment_container, mFragment[index]);
        }
        ft.show(mFragment[index]).commitAllowingStateLoss();
                //commitAllowingStateLoss();
    }

    private void setRadioStatus() {
        for (int i = 0; i < rbs.length; i++) {
            if (index != i) {
                rbs[i].setChecked(false);
            } else {
                rbs[i].setChecked(true);
            }
        }
        currentIndex = index;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (index == 4 && FuLiCenterApplication.getUser() == null) {
            index = 0;
        }
        setFragment();
        setRadioStatus();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK && requestCode == I.REQUEST_CODE_LOGIN) {
            index = 4;
            setFragment();
            setRadioStatus();
        }
    }
}

















